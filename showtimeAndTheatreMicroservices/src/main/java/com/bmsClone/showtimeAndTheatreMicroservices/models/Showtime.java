package com.bmsClone.showtimeAndTheatreMicroservices.models;

import com.mongodb.lang.NonNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document("shows")
@Data
public class Showtime {
    @MongoId
    private String id;
    @NonNull
    private String movieId;
    @NonNull
    private Date startTime;
    @NonNull
    private String theatreId;
    @NonNull
    private int availableTickets;
    @NonNull
    private int price;

    public Showtime(){}

}
