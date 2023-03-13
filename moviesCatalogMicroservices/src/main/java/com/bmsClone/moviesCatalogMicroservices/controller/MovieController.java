package com.bmsClone.moviesCatalogMicroservices.controller;

import com.bmsClone.moviesCatalogMicroservices.constants.errors;
import com.bmsClone.moviesCatalogMicroservices.error.CustomError;
import com.bmsClone.moviesCatalogMicroservices.models.modelsDto.MoviesDto;
import com.bmsClone.moviesCatalogMicroservices.models.modelsDto.ResponseDto;
import com.bmsClone.moviesCatalogMicroservices.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;
    private final Environment env;

    @GetMapping("/health")
    public String getHealth() {
        return "Port : " + env.getProperty("local.server.port");
    }

    @PostMapping("/addMovie")
    public ResponseEntity<?> addMovie(@RequestBody MoviesDto moviesDto) throws Exception {
        try {
            movieService.addMovie(moviesDto);
            return ResponseEntity.ok(new ResponseDto(true, "Movie added Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getMovieDetails/{id}")
    public ResponseEntity<?> getMovieDetails(@PathVariable String id) {
        try {
            return ResponseEntity.ok(movieService.getMovieDetails(id));
        } catch (Exception e) {
            if (e instanceof CustomError)
                return ResponseEntity.status(((CustomError) e).getStatus()).body(new ResponseDto(false, e.getMessage()));
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getUpcomingMovies")
    public ResponseEntity<?> getUpcomingMovies() {
        try {
            return ResponseEntity.ok(movieService.getUpcomingMovies());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }
}
