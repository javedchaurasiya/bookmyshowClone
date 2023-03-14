package com.bmsClone.showtimeAndTheatreMicroservices.services;

import com.bmsClone.showtimeAndTheatreMicroservices.constants.errors;
import com.bmsClone.showtimeAndTheatreMicroservices.error.CustomError;
import com.bmsClone.showtimeAndTheatreMicroservices.models.Theatre;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.TheatreDto;
import com.bmsClone.showtimeAndTheatreMicroservices.repository.showtimeRepository.ShowtimeRepository;
import com.bmsClone.showtimeAndTheatreMicroservices.repository.theatreRepository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheatreService {
    private final TheatreRepository theatreRepository;
    private final ShowtimeRepository showtimeRepository;

    public void addTheatre(TheatreDto theatreDto) {
        try {
            theatreRepository.save(theatreDto.toTheatre());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errors.INTERNAL_SERVER_ERROR);
        }
    }

    public Theatre getTheatreDetails(String id) {
        try {
            Optional<Theatre> optionalTheatre = theatreRepository.findById(id);
            if (optionalTheatre.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, errors.THEATRE_NOT_FOUND);
            return optionalTheatre.get();
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errors.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Theatre> getTheatresByMovie(String id) {
        try {
            return theatreRepository.getTheatresByMovie(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errors.INTERNAL_SERVER_ERROR);
        }
    }
}
