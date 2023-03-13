package com.bmsClone.ReservationMicroservice.controllers;

import com.bmsClone.ReservationMicroservice.constants.errors;
import com.bmsClone.ReservationMicroservice.error.CustomError;
import com.bmsClone.ReservationMicroservice.models.modelsDto.ReservationDto;
import com.bmsClone.ReservationMicroservice.models.modelsDto.ResponseDto;
import com.bmsClone.ReservationMicroservice.services.ReservationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final Environment env;

    @GetMapping("/health")
    public String getHealth() {
        return "Port : " + env.getProperty("local.server.port");
    }

    @PostMapping("/addReservation")
    public ResponseEntity<?> addReservation(@RequestBody ReservationDto reservationDto) {
        try {
            reservationService.addReservation(reservationDto);
            return ResponseEntity.ok(new ResponseDto(true, "Reservation Added Successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getUpcomingReservationsByUser/{id}")
    public ResponseEntity<?> getUpcomingReservationsByUser(@PathVariable String id) {
        try {
            return ResponseEntity.ok(reservationService.getUpcomingReservationByUser(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/cancelReservation/{id}")
    public ResponseEntity<?> cancelReservation(@PathVariable String id) {
        try {
            reservationService.cancelReservation(id);
            return ResponseEntity.ok(new ResponseDto(true, "Successfully Cancelled Reservation"));
        } catch (Exception e) {
            if (e instanceof CustomError)
                return ResponseEntity.status(((CustomError) e).getStatus()).body(new ResponseDto(false, e.getMessage()));
            return ResponseEntity.internalServerError().body(new ResponseDto(false, errors.INTERNAL_SERVER_ERROR));
        }
    }
}
