package com.bmsClone.showtimeAndTheatreMicroservices.repository.theatreRepository;

import com.bmsClone.showtimeAndTheatreMicroservices.models.Theatre;
import com.bmsClone.showtimeAndTheatreMicroservices.repository.showtimeRepository.ShowtimeRepository;
import lombok.Data;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Data
public class TheatreRepositoryImpl implements TheatreRepositoryCustom {
    private final MongoTemplate mongoTemplate;
    private final ShowtimeRepository showtimeRepository;

    @Override
    public List<Theatre> getTheatresByMovie(String id) {
        List<String> theatreIds = showtimeRepository.getTheatreIdsOfUpcomingShowsByMovie(id);
        Query query = new Query();
        query.addCriteria(Criteria.where("id").in(theatreIds));
        return mongoTemplate.find(query, Theatre.class);
    }
}
