package com.bmsClone.showtimeAndTheatreMicroservices.models.responseModels;

import com.bmsClone.showtimeAndTheatreMicroservices.models.Showtime;
import com.bmsClone.showtimeAndTheatreMicroservices.models.Theatre;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class ShowtimeAndTheatre {
    private String id;
    private Movie movie;
    private Theatre theatre;
    private Date startTime;
    private int availableTickets;
    private int price;
}
