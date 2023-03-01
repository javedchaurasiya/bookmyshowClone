package com.bmsClone.ReservationMicroservice.repository;

import com.bmsClone.ReservationMicroservice.models.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
    public List<Reservation> findReservationByUserIdAndCancelled(String id, Boolean cancelled);
}
