package com.bmsClone.showtimeAndTheatreMicroservices.services;

import com.bmsClone.showtimeAndTheatreMicroservices.constants.errors;
import com.bmsClone.showtimeAndTheatreMicroservices.error.CustomError;
import com.bmsClone.showtimeAndTheatreMicroservices.models.Showtime;
import com.bmsClone.showtimeAndTheatreMicroservices.models.Theatre;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.ShowtimeDto;
import com.bmsClone.showtimeAndTheatreMicroservices.models.responseModels.Movie;
import com.bmsClone.showtimeAndTheatreMicroservices.models.responseModels.ShowtimeAndTheatre;
import com.bmsClone.showtimeAndTheatreMicroservices.repository.showtimeRepository.ShowtimeRepository;
import com.bmsClone.showtimeAndTheatreMicroservices.repository.theatreRepository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    private final TheatreRepository theatreRepository;
    private final RestTemplate restTemplate;
    String movieServiceUrl = "http://localhost:8082";

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

    public List<Showtime> getShowsByTheatreAndMovie(String theatreId, String movieId) throws Exception {
        try {
            // can also groupBy the data according to show date.
            return showtimeRepository.findShowtimeByTheatreIdAndMovieId(theatreId, movieId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public ShowtimeAndTheatre getShowById(String id) throws Exception {
        try {
            Optional<Showtime> optionalShowtime = showtimeRepository.findById(id);
            if (optionalShowtime.isEmpty()) throw new CustomError(404, errors.SHOWTIME_NOT_FOUND);

            Showtime showtime = optionalShowtime.get();
            Theatre theatre = theatreRepository.findById(showtime.getTheatreId()).get();

            ResponseEntity<Movie> movieResponseEntity = restTemplate.getForEntity(movieServiceUrl + "/getMovieDetails/" + showtime.getMovieId(), Movie.class);
            Movie movie = movieResponseEntity.getBody();

            return ShowtimeAndTheatre.builder()
                    .id(showtime.getId())
                    .theatre(theatre)
                    .movie(movie)
                    .startTime(showtime.getStartTime())
                    .build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


}
