package com.bmsClone.showtimeAndTheatreMicroservices.controllers;

import com.bmsClone.showtimeAndTheatreMicroservices.constants.errors;
import com.bmsClone.showtimeAndTheatreMicroservices.error.CustomError;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.TheatreDto;
import com.bmsClone.showtimeAndTheatreMicroservices.models.responseModels.Response;
import com.bmsClone.showtimeAndTheatreMicroservices.services.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TheatreController {
    private final TheatreService theatreService;


    @PostMapping("/addTheatre")
    public ResponseEntity<?> addTheatre(@RequestBody TheatreDto theatreDto) throws Exception {
        try {
            theatreService.addTheatre(theatreDto);
            return ResponseEntity.ok(new Response(true, "Theatre added Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new Response(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getTheatreDetails")
    public ResponseEntity<?> getTheatreDetails(@PathVariable String id) throws Exception {
        try {
            return ResponseEntity.ok(theatreService.getTheatreDetails(id));
        } catch (Exception e) {
            if (e instanceof CustomError)
                return ResponseEntity.status(((CustomError) e).getStatus()).body(new Response(false, e.getMessage()));
            return ResponseEntity.internalServerError().body(new Response(false, errors.INTERNAL_SERVER_ERROR));
        }
    }
}
