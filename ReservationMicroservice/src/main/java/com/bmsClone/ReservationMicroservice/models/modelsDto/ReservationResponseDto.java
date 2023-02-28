package com.bmsClone.ReservationMicroservice.models.modelsDto;

import lombok.*;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ReservationResponseDto {
    private String id;
    private ShowtimeDto showtime;
    private String userId;
    private int noOfTickets;
}
