package com.bmsClone.userMicroservices.repository.userRepository;

import com.bmsClone.userMicroservices.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
}
