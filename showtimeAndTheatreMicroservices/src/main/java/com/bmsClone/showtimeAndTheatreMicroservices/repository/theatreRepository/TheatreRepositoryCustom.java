package com.bmsClone.showtimeAndTheatreMicroservices.repository.theatreRepository;

import com.bmsClone.showtimeAndTheatreMicroservices.models.Theatre;

import java.util.List;

public interface TheatreRepositoryCustom {
    public List<Theatre> getTheatresByMovie(String id);
}
