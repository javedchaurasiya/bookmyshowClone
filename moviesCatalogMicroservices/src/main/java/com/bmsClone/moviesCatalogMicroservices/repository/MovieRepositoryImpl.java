package com.bmsClone.moviesCatalogMicroservices.repository;

import com.bmsClone.moviesCatalogMicroservices.models.Movie;
import lombok.Data;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Data
@Repository
public class MovieRepositoryImpl implements MovieRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Movie> getUpcomingMovies() {
        Query query = new Query();
        query.addCriteria(Criteria.where("releaseDate").gte(new Date()));
        return mongoTemplate.find(query, Movie.class);
    }
}
