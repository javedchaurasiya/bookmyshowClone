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
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setCastCrew(castCrew);
        movie.setLengthInMinutes(lengthInMinutes);
        //will add validation later
        movie.setReleaseDate(dateParser());
        return movie;
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
