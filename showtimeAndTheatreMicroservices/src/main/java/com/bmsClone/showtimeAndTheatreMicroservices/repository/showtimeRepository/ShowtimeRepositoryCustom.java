package com.bmsClone.showtimeAndTheatreMicroservices.repository.showtimeRepository;

import java.util.List;

public interface ShowtimeRepositoryCustom {
    List<String> getTheatreIdsOfUpcomingShowsByMovie(String id);
}
