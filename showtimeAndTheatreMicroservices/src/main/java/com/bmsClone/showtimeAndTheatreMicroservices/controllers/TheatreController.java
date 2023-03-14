package com.bmsClone.showtimeAndTheatreMicroservices.controllers;

import com.bmsClone.showtimeAndTheatreMicroservices.constants.errors;
import com.bmsClone.showtimeAndTheatreMicroservices.error.CustomError;
import com.bmsClone.showtimeAndTheatreMicroservices.models.Theatre;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.TheatreDto;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.ResponseDto;
import com.bmsClone.showtimeAndTheatreMicroservices.services.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/showtime-and-theatre")
public class TheatreController {
    private final TheatreService theatreService;


    @PostMapping("/addTheatre")
    public ResponseEntity<ResponseDto> addTheatre(@RequestBody TheatreDto theatreDto) {
        theatreService.addTheatre(theatreDto);
        return ResponseEntity.ok(new ResponseDto(true, "Theatre added Successfully"));
    }

    @GetMapping("/getTheatreDetails/{id}")
    public ResponseEntity<Theatre> getTheatreDetails(@PathVariable String id) {
        return ResponseEntity.ok(theatreService.getTheatreDetails(id));
    }

    @GetMapping("/getTheatresByMovie/{id}")
    public ResponseEntity<List<Theatre>> getTheatresByMovie(@PathVariable String id) {
        return ResponseEntity.ok(theatreService.getTheatresByMovie(id));
    }

}
