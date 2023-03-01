package com.bmsClone.showtimeAndTheatreMicroservices.repository.showtimeRepository;

import com.bmsClone.showtimeAndTheatreMicroservices.models.Showtime;
import lombok.Data;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Data
public class ShowtimeRepositoryImpl implements ShowtimeRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public List<String> getTheatreIdsOfUpcomingShowsByMovie(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("movieId").is(id));
        query.addCriteria(Criteria.where("startTime").gte(new Date()));
        query.fields().include("theatreId");
        List<Showtime> shows = mongoTemplate.find(query, Showtime.class);
        return shows.stream().map(Showtime::getTheatreId).collect(Collectors.toList());
    }
}
