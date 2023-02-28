package com.bmsClone.ReservationMicroservice.models.modelsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShowTicketsDto {
    private String id;
    private int difference;
}
