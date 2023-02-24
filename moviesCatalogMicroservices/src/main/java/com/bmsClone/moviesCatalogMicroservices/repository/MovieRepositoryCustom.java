package com.bmsClone.moviesCatalogMicroservices.repository;

import com.bmsClone.moviesCatalogMicroservices.models.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MovieRepositoryCustom {
    public List<Movie> getUpcomingMovies();
}
