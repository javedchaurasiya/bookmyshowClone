package com.bmsClone.ReservationMicroservice.services;

import com.bmsClone.ReservationMicroservice.constants.errors;
import com.bmsClone.ReservationMicroservice.error.CustomError;
import com.bmsClone.ReservationMicroservice.feignClient.ShowtimeAndTheatreServiceClient;
import com.bmsClone.ReservationMicroservice.models.Reservation;
import com.bmsClone.ReservationMicroservice.models.modelsDto.ReservationDto;
import com.bmsClone.ReservationMicroservice.models.modelsDto.ShowtimeDto;
import com.bmsClone.ReservationMicroservice.models.modelsDto.ReservationResponseDto;
import com.bmsClone.ReservationMicroservice.models.modelsDto.UpdateShowTicketsDto;
import com.bmsClone.ReservationMicroservice.repository.ReservationRepository;
import feign.FeignException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ShowtimeAndTheatreServiceClient showtimeAndTheatreServiceClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public void addReservation(ReservationDto reservationDto, String userId) {
        try {
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");

            reservationDto.setUserId(userId);

            //circuit breaker logic.
            circuitBreaker.run(() -> {
                showtimeAndTheatreServiceClient.updateAvailableTickets(new UpdateShowTicketsDto(reservationDto.getShowtimeId(), -reservationDto.getNoOfTickets()));
                return null;
            }, throwable -> {
                if (throwable instanceof FeignException && ((FeignException) throwable).status() == 400)
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unavailable Tickets");
                throw new RuntimeException(throwable);
            });
            
            reservationRepository.save(reservationDto.toReservation());
            //to make it more efficient, can also add a ref. of reservation to the particular user.
        } catch (ResponseStatusException e) {
            System.out.println("Caught a ResponseStatusException");
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<ReservationResponseDto> getUpcomingReservationByUser(String id) {
        try {
            List<Reservation> reservations = reservationRepository.findReservationByUserIdAndCancelled(id, false);

            List<ReservationResponseDto> reservationResponses = new ArrayList<>();

            reservations.forEach(reservation -> {
                try {
                    ResponseEntity<ShowtimeDto> showtimeDtoResponseEntity = showtimeAndTheatreServiceClient.getShowById(reservation.getShowtimeId());
                    ShowtimeDto showtime = showtimeDtoResponseEntity.getBody();
                    System.out.println(showtime);
                    if (new Date().before(showtime.getStartTime())) {
                        ReservationResponseDto reservationResponse = ReservationResponseDto.builder()
                                .id(reservation.getId())
                                .showtime(showtime)
                                .userId(reservation.getUserId())
                                .noOfTickets(reservation.getNoOfTickets())
                                .build();
                        reservationResponses.add(reservationResponse);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
            return reservationResponses;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public void cancelReservation(String id, String userId) {
        try {
            //assuming user input is valid and reservation always exists.
            Optional<Reservation> optionalReservation = reservationRepository.findById(id);
            if (optionalReservation.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, errors.RESERVATION_NOT_FOUND);

            Reservation reservation = optionalReservation.get();

            if (!reservation.getUserId().equals(userId)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

            if (reservation.getCancelled())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation is already cancelled");

            reservation.setCancelled(true);
            showtimeAndTheatreServiceClient.updateAvailableTickets(new UpdateShowTicketsDto(reservation.getShowtimeId(), reservation.getNoOfTickets()));
            reservationRepository.save(reservation);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
