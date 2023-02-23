package com.bmsClone.showtimeAndTheatreMicroservices.services;

import com.bmsClone.showtimeAndTheatreMicroservices.models.Showtime;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.ShowtimeDto;
import com.bmsClone.showtimeAndTheatreMicroservices.repository.showtimeRepository.ShowtimeRepository;
import com.bmsClone.showtimeAndTheatreMicroservices.repository.theatreRepository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    private final TheatreRepository theatreRepository;

    public void addShow(ShowtimeDto showtimeDto) throws Exception {
        try {
            Showtime showtime = showtimeDto.toShowtime();
            //forcing the optional theatre, not doing server side input validation for now.
            showtime.setAvailableTickets(theatreRepository.findById(showtime.getTheatreId()).get().getCapacity());
            showtimeRepository.save(showtimeDto.toShowtime());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
