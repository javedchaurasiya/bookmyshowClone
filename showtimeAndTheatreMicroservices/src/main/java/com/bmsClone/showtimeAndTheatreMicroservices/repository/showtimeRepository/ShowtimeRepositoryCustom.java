package com.bmsClone.showtimeAndTheatreMicroservices.repository.showtimeRepository;

import com.bmsClone.showtimeAndTheatreMicroservices.models.Showtime;

import java.util.List;

public interface ShowtimeRepositoryCustom {
    List<String> getTheatreIdsOfUpcomingShowsByMovie(String id);
}
