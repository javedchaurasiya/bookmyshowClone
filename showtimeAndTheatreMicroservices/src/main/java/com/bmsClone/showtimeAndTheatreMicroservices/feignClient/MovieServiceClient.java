package com.bmsClone.showtimeAndTheatreMicroservices.feignClient;

import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.MovieDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("movie-service")
public interface MovieServiceClient {
    @GetMapping("/movie/getMovieDetails/{movieId}")
    ResponseEntity<MovieDto> getMovieDetails(@PathVariable String movieId);
}
