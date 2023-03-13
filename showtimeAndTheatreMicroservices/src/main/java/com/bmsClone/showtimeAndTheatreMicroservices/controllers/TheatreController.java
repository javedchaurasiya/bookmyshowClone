package com.bmsClone.showtimeAndTheatreMicroservices.controllers;

import com.bmsClone.showtimeAndTheatreMicroservices.constants.errors;
import com.bmsClone.showtimeAndTheatreMicroservices.error.CustomError;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.TheatreDto;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.ResponseDto;
import com.bmsClone.showtimeAndTheatreMicroservices.services.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/showtime-and-theatre")
public class TheatreController {
    private final TheatreService theatreService;


    @PostMapping("/addTheatre")
    public ResponseEntity<?> addTheatre(@RequestBody TheatreDto theatreDto) {
        try {
            theatreService.addTheatre(theatreDto);
            return ResponseEntity.ok(new ResponseDto(true, "Theatre added Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getTheatreDetails/{id}")
    public ResponseEntity<?> getTheatreDetails(@PathVariable String id) {
        try {
            return ResponseEntity.ok(theatreService.getTheatreDetails(id));
        } catch (Exception e) {
            if (e instanceof CustomError)
                return ResponseEntity.status(((CustomError) e).getStatus()).body(new ResponseDto(false, e.getMessage()));
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getTheatresByMovie/{id}")
    public ResponseEntity<?> getTheatresByMovie(@PathVariable String id) {
        try {
            return ResponseEntity.ok(theatreService.getTheatresByMovie(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

}
