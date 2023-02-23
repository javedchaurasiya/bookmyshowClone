package com.bmsClone.moviesCatalogMicroservices.models;

import com.mongodb.lang.NonNull;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Document(value = "movies")
public class Movie {
    @MongoId
    private String id;
    @NonNull
    private String title;
    @NonNull
    private int lengthInMinutes;
    @NonNull
    private String castCrew;

    @NonNull
    private Date releaseDate;

    public Movie() {
    }
}
