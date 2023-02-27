package com.bmsClone.ReservationMicroservice.controllers;

import com.bmsClone.ReservationMicroservice.constants.errors;
import com.bmsClone.ReservationMicroservice.models.modelsDto.ReservationDto;
import com.bmsClone.ReservationMicroservice.models.responseModels.Response;
import com.bmsClone.ReservationMicroservice.services.ReservationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/addReservation")
    public ResponseEntity<?> addReservation(@RequestBody ReservationDto reservationDto) {
        try {
            reservationService.addReservation(reservationDto);
            return ResponseEntity.ok(new Response(true, "Reservation Added Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new Response(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getUpcomingReservationsByUser/{id}")
    public ResponseEntity<?> getUpcomingReservationsByUser(@PathVariable String id) {
        try {
            return ResponseEntity.ok(reservationService.getUpcomingReservationByUser(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new Response(false, errors.INTERNAL_SERVER_ERROR));
        }
    }
}
