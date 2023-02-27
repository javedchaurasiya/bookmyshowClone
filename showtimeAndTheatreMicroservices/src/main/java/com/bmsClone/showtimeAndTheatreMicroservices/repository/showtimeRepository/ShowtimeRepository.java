package com.bmsClone.showtimeAndTheatreMicroservices.repository.showtimeRepository;

import com.bmsClone.showtimeAndTheatreMicroservices.models.Showtime;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShowtimeRepository extends MongoRepository<Showtime, String>, ShowtimeRepositoryCustom {
    public List<Showtime> findShowtimeByTheatreIdAndMovieId(String theatreId, String movieId);
}
