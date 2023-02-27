package com.bmsClone.ReservationMicroservice.services;

import com.bmsClone.ReservationMicroservice.models.Reservation;
import com.bmsClone.ReservationMicroservice.models.modelsDto.ReservationDto;
import com.bmsClone.ReservationMicroservice.models.modelsDto.ShowtimeDto;
import com.bmsClone.ReservationMicroservice.models.responseModels.ReservationResponse;
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
    String showtimeAndTheatreServiceUrl = "http://localhost:8083";

    public void addReservation(ReservationDto reservationDto) throws Exception {
        try {
            reservationRepository.save(reservationDto.toReservation());
            //to make it more efficient, can also add a ref. of reservation to the particular user.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<ReservationResponse> getUpcomingReservationByUser(String id) throws Exception {
        try {
            List<Reservation> reservations = reservationRepository.findReservationByUserId(id);

            List<ReservationResponse> reservationResponses = new ArrayList<>();

            reservations.forEach(reservation -> {
                try {
                    ResponseEntity<ShowtimeDto> showtimeDtoResponseEntity = restTemplate.getForEntity(showtimeAndTheatreServiceUrl + "/getShowById/" + reservation.getShowtimeId(), ShowtimeDto.class);
                    ShowtimeDto showtime = showtimeDtoResponseEntity.getBody();
                    System.out.println(showtime);
                    if (new Date().before(showtime.getStartTime())) {
                        ReservationResponse reservationResponse = ReservationResponse.builder()
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
}
