package com.bmsClone.moviesCatalogMicroservices.services;

import com.bmsClone.moviesCatalogMicroservices.constants.errors;
import com.bmsClone.moviesCatalogMicroservices.error.CustomError;
import com.bmsClone.moviesCatalogMicroservices.models.Movie;
import com.bmsClone.moviesCatalogMicroservices.models.modelsDto.MoviesDto;
import com.bmsClone.moviesCatalogMicroservices.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public void addMovie(MoviesDto moviesDto) throws Exception {
        try {
            movieRepository.save(moviesDto.toMovie());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<Movie> getUpcomingMovies() throws Exception {
        try {
            return movieRepository.getUpcomingMovies();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Movie getMovieDetails(String id) throws Exception {
        try {
            Optional<Movie> optionalMovie = movieRepository.findById(id);
            if (optionalMovie.isEmpty()) throw new CustomError(404, errors.MOVIE_NOT_FOUND);

            return optionalMovie.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
