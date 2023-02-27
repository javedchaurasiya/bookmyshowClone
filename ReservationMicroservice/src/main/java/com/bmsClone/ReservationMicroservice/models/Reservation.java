package com.bmsClone.ReservationMicroservice.models;

import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("reservations")
public class Reservation {
    @MongoId
    private String id;
    @NonNull
    private String showtimeId;
    @NonNull
    private String userId;
    @NonNull
    private int noOfTickets;
}
