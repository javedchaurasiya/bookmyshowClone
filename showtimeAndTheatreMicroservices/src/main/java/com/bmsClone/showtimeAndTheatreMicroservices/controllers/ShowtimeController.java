package com.bmsClone.showtimeAndTheatreMicroservices.controllers;

import com.bmsClone.showtimeAndTheatreMicroservices.constants.errors;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.ShowtimeDto;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.ResponseDto;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.UpdateShowTicketsDto;
import com.bmsClone.showtimeAndTheatreMicroservices.services.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ShowtimeController {
    private final ShowtimeService showtimeService;
    private final Environment env;

    @GetMapping("/health")
    public String getHealth() {
        return "Port : " + env.getProperty("local.server.port");
    }

    @PostMapping("/addShow")
    public ResponseEntity<?> addShow(@RequestBody ShowtimeDto showtimeDto) {
        try {
            showtimeService.addShow(showtimeDto);
            return ResponseEntity.ok(new ResponseDto(true, "Show Added Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getShowsByTheatreAndMovie")
    public ResponseEntity<?> getShowsByTheatreAndMovie(@RequestParam(value = "theatre") String theatreId, @RequestParam(value = "movie") String movieId) {
        try {
            return ResponseEntity.ok(showtimeService.getShowsByTheatreAndMovie(theatreId, movieId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getShowById/{id}")
    public ResponseEntity<?> getShowById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(showtimeService.getShowById(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    //can i call some endpoints internally b/w my microservices?
    //can also use this endpoint for cancelling reservations.
    @PutMapping("/updateAvailableTickets")
    public ResponseEntity<?> updateAvailableTickets(@RequestBody UpdateShowTicketsDto updateShowTicketsDto) {
        try {
            showtimeService.updateAvailableTickets(updateShowTicketsDto);
            return ResponseEntity.ok(new ResponseDto(true, "Successfully Updated Available Tickets"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }
}
