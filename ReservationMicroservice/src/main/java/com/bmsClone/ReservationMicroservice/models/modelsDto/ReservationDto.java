package com.bmsClone.ReservationMicroservice.models.modelsDto;

import com.bmsClone.ReservationMicroservice.models.Reservation;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReservationDto {
    private String showtimeId;
    private String userId;
    private int noOfTickets;

    public Reservation toReservation() {
        return Reservation.builder()
                .showtimeId(this.showtimeId)
                .noOfTickets(this.noOfTickets)
                .userId(this.userId)
                .build();
    }
}
