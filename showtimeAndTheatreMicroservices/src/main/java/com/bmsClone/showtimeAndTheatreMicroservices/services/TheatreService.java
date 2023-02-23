package com.bmsClone.showtimeAndTheatreMicroservices.services;

import com.bmsClone.showtimeAndTheatreMicroservices.constants.errors;
import com.bmsClone.showtimeAndTheatreMicroservices.error.CustomError;
import com.bmsClone.showtimeAndTheatreMicroservices.models.Theatre;
import com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto.TheatreDto;
import com.bmsClone.showtimeAndTheatreMicroservices.repository.theatreRepository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheatreService {
    private final TheatreRepository theatreRepository;

    public void addTheatre(TheatreDto theatreDto) throws Exception {
        try {
            theatreRepository.save(theatreDto.toTheatre());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Theatre getTheatreDetails(String id) throws Exception {
        try {
            Optional<Theatre> optionalTheatre = theatreRepository.findById(id);
            if (optionalTheatre.isEmpty()) throw new CustomError(404, errors.THEATRE_NOT_FOUND);
            return optionalTheatre.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
