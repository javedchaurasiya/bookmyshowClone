package com.bmsClone.moviesCatalogMicroservices.services;

import com.bmsClone.moviesCatalogMicroservices.constants.errors;
import com.bmsClone.moviesCatalogMicroservices.error.CustomError;
import com.bmsClone.moviesCatalogMicroservices.models.Movie;
import com.bmsClone.moviesCatalogMicroservices.models.modelsDto.MoviesDto;
import com.bmsClone.moviesCatalogMicroservices.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public void addMovie(MoviesDto moviesDto) {
        try {
            movieRepository.save(moviesDto.toMovie());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errors.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Movie> getUpcomingMovies() {
        try {
            return movieRepository.getUpcomingMovies();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errors.INTERNAL_SERVER_ERROR);
        }
    }

    public Movie getMovieDetails(String id) throws ResponseStatusException {
        try {
            Optional<Movie> optionalMovie = movieRepository.findById(id);
            if (optionalMovie.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie Not Found");
            return optionalMovie.get();
        } catch (ResponseStatusException e) {
            System.out.println(e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errors.INTERNAL_SERVER_ERROR);
        }
    }

}
