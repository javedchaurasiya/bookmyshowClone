package com.bmsClone.ReservationMicroservice.repository;

import com.bmsClone.ReservationMicroservice.models.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation,String> {
    public List<Reservation> findReservationByUserId(String id);
}
