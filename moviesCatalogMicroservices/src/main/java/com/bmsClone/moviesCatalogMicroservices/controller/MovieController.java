package com.bmsClone.moviesCatalogMicroservices.controller;

import com.bmsClone.moviesCatalogMicroservices.constants.errors;
import com.bmsClone.moviesCatalogMicroservices.error.CustomError;
import com.bmsClone.moviesCatalogMicroservices.models.Movie;
import com.bmsClone.moviesCatalogMicroservices.models.modelsDto.MoviesDto;
import com.bmsClone.moviesCatalogMicroservices.models.modelsDto.ResponseDto;
import com.bmsClone.moviesCatalogMicroservices.services.MovieService;
import com.bmsClone.moviesCatalogMicroservices.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;
    private final Environment env;
    private final Util util;

    @GetMapping("/health")
    public String getHealth() {
        return "Port : " + env.getProperty("local.server.port");
    }

    @PostMapping("/addMovie")
    public ResponseEntity<ResponseDto> addMovie(@RequestBody MoviesDto moviesDto, @RequestHeader Map<String, String> headers) {
        util.throwErrorIfNotAdmin(headers);
        movieService.addMovie(moviesDto);
        return ResponseEntity.ok(new ResponseDto(true, "Movie added Successfully"));
    }

    @GetMapping("/getMovieDetails/{id}")
    public ResponseEntity<Movie> getMovieDetails(@PathVariable String id) {
        return ResponseEntity.ok(movieService.getMovieDetails(id));
    }

    @GetMapping("/getUpcomingMovies")
    public ResponseEntity<List<Movie>> getUpcomingMovies() {
        return ResponseEntity.ok(movieService.getUpcomingMovies());
    }
}
