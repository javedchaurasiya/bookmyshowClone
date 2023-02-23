package com.bmsClone.moviesCatalogMicroservices.controller;

import com.bmsClone.moviesCatalogMicroservices.constants.errors;
import com.bmsClone.moviesCatalogMicroservices.error.CustomError;
import com.bmsClone.moviesCatalogMicroservices.models.modelsDto.MoviesDto;
import com.bmsClone.moviesCatalogMicroservices.models.responseModels.Response;
import com.bmsClone.moviesCatalogMicroservices.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/addMovie")
    public ResponseEntity<?> addMovie(@RequestBody MoviesDto moviesDto) throws Exception {
        try {
            movieService.addMovie(moviesDto);
            return ResponseEntity.ok(new Response(true, "Movie added Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new Response(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getMovieDetails/{id}")
    public ResponseEntity<?> getMovieDetails(@PathVariable String id) throws Exception {
        try {
            return ResponseEntity.ok(movieService.getMovieDetails(id));
        } catch (Exception e) {
            if (e instanceof CustomError)
                return ResponseEntity.status(((CustomError) e).getStatus()).body(new Response(false, e.getMessage()));
            return ResponseEntity.internalServerError().body(new Response(false, errors.INTERNAL_SERVER_ERROR));
        }
    }
}
