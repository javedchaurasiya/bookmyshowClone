package com.bmsClone.showtimeAndTheatreMicroservices.controllers;

import com.bmsClone.showtimeAndTheatreMicroservices.constants.errors;
import com.bmsClone.showtimeAndTheatreMicroservices.models.Showtime;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.ShowtimeAndTheatreDto;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.ShowtimeDto;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.ResponseDto;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.UpdateShowTicketsDto;
import com.bmsClone.showtimeAndTheatreMicroservices.services.ShowtimeService;
import com.bmsClone.showtimeAndTheatreMicroservices.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/showtime-and-theatre")
public class ShowtimeController {
    private final ShowtimeService showtimeService;
    private final Environment env;
    private final Util util;

    @GetMapping("/health")
    public String getHealth() {
        return "Port : " + env.getProperty("local.server.port");
    }

    @PostMapping("/addShow")
    public ResponseEntity<ResponseDto> addShow(@RequestBody ShowtimeDto showtimeDto, @RequestHeader Map<String, String> headers) {
        util.throwErrorIfNotAdmin(headers);
        showtimeService.addShow(showtimeDto);
        return ResponseEntity.ok(new ResponseDto(true, "Show Added Successfully"));
    }

    @GetMapping("/getShowsByTheatreAndMovie")
    public ResponseEntity<List<Showtime>> getShowsByTheatreAndMovie(@RequestParam(value = "theatre") String theatreId, @RequestParam(value = "movie") String movieId) {
        return ResponseEntity.ok(showtimeService.getShowsByTheatreAndMovie(theatreId, movieId));
    }

    @GetMapping("/getShowById/{id}")
    public ResponseEntity<ShowtimeAndTheatreDto> getShowById(@PathVariable String id) {
        return ResponseEntity.ok(showtimeService.getShowById(id));
    }

    //can I call some endpoints internally b/w my microservices? => not a problem while we use a gateway.
    //can also use this endpoint for cancelling reservations.
    @PutMapping("/updateAvailableTickets")
    public ResponseEntity<ResponseDto> updateAvailableTickets(@RequestBody UpdateShowTicketsDto updateShowTicketsDto) {
        showtimeService.updateAvailableTickets(updateShowTicketsDto);
        return ResponseEntity.ok(new ResponseDto(true, "Successfully Updated Available Tickets"));
    }
}
