package com.bmsClone.ReservationMicroservice.services;

import com.bmsClone.ReservationMicroservice.error.CustomError;
import com.bmsClone.ReservationMicroservice.models.Reservation;
import com.bmsClone.ReservationMicroservice.models.modelsDto.ReservationDto;
import com.bmsClone.ReservationMicroservice.models.modelsDto.ShowtimeDto;
import com.bmsClone.ReservationMicroservice.models.modelsDto.ReservationResponseDto;
import com.bmsClone.ReservationMicroservice.models.modelsDto.UpdateShowTicketsDto;
import com.bmsClone.ReservationMicroservice.repository.ReservationRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RestTemplate restTemplate;
    private final String showtimeAndTheatreServiceUrl = "http://showtime-and-theatre-service/showtime-and-theatre";

    public void addReservation(ReservationDto reservationDto) throws Exception {
        try {
            restTemplate.put(showtimeAndTheatreServiceUrl + "/updateAvailableTickets", new UpdateShowTicketsDto(reservationDto.getShowtimeId(), -reservationDto.getNoOfTickets()));
            reservationRepository.save(reservationDto.toReservation());
            //to make it more efficient, can also add a ref. of reservation to the particular user.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<ReservationResponseDto> getUpcomingReservationByUser(String id) throws Exception {
        try {
            List<Reservation> reservations = reservationRepository.findReservationByUserIdAndCancelled(id, false);

            List<ReservationResponseDto> reservationResponses = new ArrayList<>();

            reservations.forEach(reservation -> {
                try {
                    //can use feign client to solve this problem of re-declaring dtos in every microservice.
                    ResponseEntity<ShowtimeDto> showtimeDtoResponseEntity = restTemplate.getForEntity(showtimeAndTheatreServiceUrl + "/getShowById/" + reservation.getShowtimeId(), ShowtimeDto.class);
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
            throw e;
        }
    }

    public void cancelReservation(String id) throws Exception {
        try {
            //assuming user input is valid and reservation always exists.
            Reservation reservation = reservationRepository.findById(id).get();
            if (reservation.getCancelled()) throw new CustomError(400, "Reservation is already cancelled");

            reservation.setCancelled(true);
            restTemplate.put(showtimeAndTheatreServiceUrl + "/updateAvailableTickets", new UpdateShowTicketsDto(reservation.getShowtimeId(), reservation.getNoOfTickets()));

            reservationRepository.save(reservation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
