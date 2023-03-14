package com.bmsClone.ReservationMicroservice.controllers;

import com.bmsClone.ReservationMicroservice.constants.errors;
import com.bmsClone.ReservationMicroservice.error.CustomError;
import com.bmsClone.ReservationMicroservice.models.modelsDto.ReservationDto;
import com.bmsClone.ReservationMicroservice.models.modelsDto.ReservationResponseDto;
import com.bmsClone.ReservationMicroservice.models.modelsDto.ResponseDto;
import com.bmsClone.ReservationMicroservice.services.ReservationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final Environment env;

    @GetMapping("/health")
    public String getHealth(@RequestHeader Map<String, String> headers) {
        System.out.println(headers.get("id"));
        System.out.println(Boolean.getBoolean(headers.get("admin")));
        return "Port : " + env.getProperty("local.server.port");
    }

    @PostMapping("/addReservation")
    public ResponseEntity<ResponseDto> addReservation(@RequestBody ReservationDto reservationDto, @RequestHeader Map<String, String> headers) {
        reservationService.addReservation(reservationDto, headers.get("id"));
        return ResponseEntity.ok(new ResponseDto(true, "Reservation Added Successfully"));
    }

    @GetMapping("/getUpcomingReservationsByUser")
    public ResponseEntity<List<ReservationResponseDto>> getUpcomingReservationsByUser(@RequestHeader Map<String, String> headers) {
        return ResponseEntity.ok(reservationService.getUpcomingReservationByUser(headers.get("id")));
    }

    @PutMapping("/cancelReservation/{id}")
    public ResponseEntity<ResponseDto> cancelReservation(@PathVariable String id, @RequestHeader Map<String, String> headers) {
        reservationService.cancelReservation(id, headers.get("id"));
        return ResponseEntity.ok(new ResponseDto(true, "Successfully Cancelled Reservation"));
    }
}
