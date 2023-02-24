package com.bmsClone.showtimeAndTheatreMicroservices.services;

import com.bmsClone.showtimeAndTheatreMicroservices.models.Showtime;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.ShowtimeDto;
import com.bmsClone.showtimeAndTheatreMicroservices.models.responseModels.ShowtimeAndTheatre;
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
            showtimeRepository.save(showtime);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

//    public ShowtimeAndTheatre getShowsByMovie(String id) throws Exception {
//        try {
//            //1 > Call the movie service to fetch the movie details by its id. (not necessary though)
//            //2 > get
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            throw e;
//        }
//    }


}
