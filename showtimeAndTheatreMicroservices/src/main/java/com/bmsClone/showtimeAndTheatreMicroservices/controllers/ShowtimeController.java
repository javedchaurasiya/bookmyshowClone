package com.bmsClone.showtimeAndTheatreMicroservices.controllers;

import com.bmsClone.showtimeAndTheatreMicroservices.constants.errors;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.ShowtimeDto;
import com.bmsClone.showtimeAndTheatreMicroservices.models.responseModels.Response;
import com.bmsClone.showtimeAndTheatreMicroservices.services.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ShowtimeController {
    private final ShowtimeService showtimeService;

    @PostMapping("/addShow")
    public ResponseEntity<?> addShow(@RequestBody ShowtimeDto showtimeDto) {
        try {
            showtimeService.addShow(showtimeDto);
            return ResponseEntity.ok(new Response(true, "Show Added Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new Response(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getShowsByTheatreAndMovie")
    public ResponseEntity<?> getShowsByTheatreAndMovie(@RequestParam(value = "theatre") String theatreId, @RequestParam(value = "movie") String movieId) {
        try {
            return ResponseEntity.ok(showtimeService.getShowsByTheatreAndMovie(theatreId, movieId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new Response(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getShowById/{id}")
    public ResponseEntity<?> getShowById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(showtimeService.getShowById(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new Response(false, errors.INTERNAL_SERVER_ERROR));
        }
    }
}
