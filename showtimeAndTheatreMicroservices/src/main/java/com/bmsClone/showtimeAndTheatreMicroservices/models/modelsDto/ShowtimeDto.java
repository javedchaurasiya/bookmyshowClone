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
        Showtime showtime = new Showtime();
        showtime.setMovieId(movieId);
        showtime.setPrice(price);
//        showtime.setAvailableTickets(availableTickets);
        showtime.setTheatreId(theatreId);
        showtime.setStartTime(startTimeParser());
        return showtime;
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
