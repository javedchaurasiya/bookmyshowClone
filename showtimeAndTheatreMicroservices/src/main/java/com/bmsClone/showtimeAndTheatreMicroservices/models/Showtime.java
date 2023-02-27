package com.bmsClone.showtimeAndTheatreMicroservices.models;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document("shows")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    
}
