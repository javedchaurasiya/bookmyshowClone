package com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto;

import lombok.Data;

@Data
public class ShowtimeDto {
    private String movieId;
    private String startTime;
    private String theatreId;
    private int availableTickets;
    private int price;
}
