package com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto;

import com.bmsClone.showtimeAndTheatreMicroservices.models.Theatre;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowtimeAndTheatreDto {
    private String id;
    private MovieDto movie;
    private Theatre theatre;
    private Date startTime;
    private int availableTickets;
    private int price;
}
