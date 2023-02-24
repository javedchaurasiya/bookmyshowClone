package com.bmsClone.showtimeAndTheatreMicroservices.repository.showtimeRepository;

import com.bmsClone.showtimeAndTheatreMicroservices.models.Showtime;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowtimeRepository extends MongoRepository<Showtime, String>, ShowtimeRepositoryCustom {
}
