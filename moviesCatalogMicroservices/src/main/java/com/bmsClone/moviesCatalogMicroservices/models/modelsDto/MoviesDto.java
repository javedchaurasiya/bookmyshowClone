package com.bmsClone.moviesCatalogMicroservices.models.modelsDto;

import com.bmsClone.moviesCatalogMicroservices.models.Movie;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class MoviesDto {
    private String title;
    private int lengthInMinutes;
    private String castCrew;
    private String releaseDate;

    public MoviesDto() {

    }

    public Movie toMovie() {
        return Movie.builder()
                .title(title)
                .castCrew(castCrew)
                .lengthInMinutes(lengthInMinutes)
                .releaseDate(dateParser())
                .build();
    }

    private Date dateParser() {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(releaseDate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Date();
        }
    }
}
