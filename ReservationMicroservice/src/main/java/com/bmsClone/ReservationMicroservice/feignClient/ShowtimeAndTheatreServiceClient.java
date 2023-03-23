package com.bmsClone.ReservationMicroservice.feignClient;

import com.bmsClone.ReservationMicroservice.models.modelsDto.ShowtimeDto;
import com.bmsClone.ReservationMicroservice.models.modelsDto.UpdateShowTicketsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "showtime-and-theatre-service")
public interface ShowtimeAndTheatreServiceClient {
    @GetMapping("/showtime-and-theatre/getShowById/{showId}")
    ResponseEntity<ShowtimeDto> getShowById(@PathVariable String showId);

    @PutMapping("/showtime-and-theatre/updateAvailableTickets")
    void updateAvailableTickets(@RequestBody UpdateShowTicketsDto updateShowTicketsDto);
}
