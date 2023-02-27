package com.bmsClone.ReservationMicroservice.models.modelsDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class ShowtimeDto {
    private String id;
    private Object movie;
    private Object theatre;
    private Date startTime;
}
