package com.bmsClone.ReservationMicroservice.models.responseModels;

import com.bmsClone.ReservationMicroservice.models.modelsDto.ShowtimeDto;
import lombok.*;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ReservationResponse {
    private String id;
    private ShowtimeDto showtime;
    private String userId;
    private int noOfTickets;
}
