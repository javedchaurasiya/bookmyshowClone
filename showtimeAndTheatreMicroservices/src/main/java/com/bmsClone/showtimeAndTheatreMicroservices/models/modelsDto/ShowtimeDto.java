package com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto;

import com.bmsClone.showtimeAndTheatreMicroservices.models.Showtime;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ShowtimeDto {
    private String movieId;
    private String startTime;
    private String theatreId;
    private int availableTickets;
    private int price;

    public ShowtimeDto() {
    }

    public Showtime toShowtime() {
        return Showtime.builder()
                .movieId(movieId)
                .price(price)
                .availableTickets(availableTickets)
                .theatreId(theatreId)
                .startTime(startTimeParser())
                .build();
    }

    private Date startTimeParser() {
        try {
            return new SimpleDateFormat("dd-MM-yyyy hh:mm").parse(startTime);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Date();
        }
    }
}
