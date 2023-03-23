package com.bmsClone.showtimeAndTheatreMicroservices.services;

import com.bmsClone.showtimeAndTheatreMicroservices.constants.errors;
import com.bmsClone.showtimeAndTheatreMicroservices.error.CustomError;
import com.bmsClone.showtimeAndTheatreMicroservices.feignClient.MovieServiceClient;
import com.bmsClone.showtimeAndTheatreMicroservices.models.Showtime;
import com.bmsClone.showtimeAndTheatreMicroservices.models.Theatre;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.ShowtimeDto;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.MovieDto;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.ShowtimeAndTheatreDto;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.UpdateShowTicketsDto;
import com.bmsClone.showtimeAndTheatreMicroservices.repository.showtimeRepository.ShowtimeRepository;
import com.bmsClone.showtimeAndTheatreMicroservices.repository.theatreRepository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    private final TheatreRepository theatreRepository;
    private final MovieServiceClient movieServiceClient;

    public void addShow(ShowtimeDto showtimeDto) {
        try {
            Showtime showtime = showtimeDto.toShowtime();
            //forcing the optional theatre, not doing server side input validation for now.
            showtime.setAvailableTickets(theatreRepository.findById(showtime.getTheatreId()).get().getCapacity());
            showtimeRepository.save(showtime);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errors.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Showtime> getShowsByTheatreAndMovie(String theatreId, String movieId) {
        try {
            // can also groupBy the data according to show date.
            return showtimeRepository.findShowtimeByTheatreIdAndMovieId(theatreId, movieId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errors.INTERNAL_SERVER_ERROR);
        }
    }

    public ShowtimeAndTheatreDto getShowById(String id) {
        try {
            Optional<Showtime> optionalShowtime = showtimeRepository.findById(id);
            if (optionalShowtime.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, errors.SHOWTIME_NOT_FOUND);

            Showtime showtime = optionalShowtime.get();
            Theatre theatre = theatreRepository.findById(showtime.getTheatreId()).get();

            ResponseEntity<MovieDto> movieResponseEntity = movieServiceClient.getMovieDetails(showtime.getMovieId());
            MovieDto movie = movieResponseEntity.getBody();

            return ShowtimeAndTheatreDto.builder()
                    .id(showtime.getId())
                    .theatre(theatre)
                    .movie(movie)
                    .startTime(showtime.getStartTime())
                    .build();
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errors.INTERNAL_SERVER_ERROR);
        }
    }

    public void updateAvailableTickets(UpdateShowTicketsDto updateShowTicketsDto) {
        try {
            //Assuming show is always there
            Showtime showtime = showtimeRepository.findById(updateShowTicketsDto.getId()).get();
            if (showtime.getAvailableTickets() + updateShowTicketsDto.getDifference() < 0)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Args");
            showtime.setAvailableTickets(showtime.getAvailableTickets() + updateShowTicketsDto.getDifference());
            showtimeRepository.save(showtime);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errors.INTERNAL_SERVER_ERROR);
        }
    }

}
