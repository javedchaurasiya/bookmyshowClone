package com.bmsClone.showtimeAndTheatreMicroservices.repository.theatreRepository;

import com.bmsClone.showtimeAndTheatreMicroservices.models.Theatre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TheatreRepository extends MongoRepository<Theatre,String> {
}
