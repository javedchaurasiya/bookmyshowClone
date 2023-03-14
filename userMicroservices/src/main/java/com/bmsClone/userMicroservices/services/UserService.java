package com.bmsClone.userMicroservices.services;

import com.bmsClone.userMicroservices.constants.errors;
import com.bmsClone.userMicroservices.error.CustomError;
import com.bmsClone.userMicroservices.models.User;
import com.bmsClone.userMicroservices.models.dtoModels.UserDto;
import com.bmsClone.userMicroservices.repository.userRepository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

//    public void signup(UserDto userDto) throws Exception {
//        try {
//            //will add hashing later.
//            userRepository.save(userDto.toUser());
//        } catch (Exception e) {
//            //will add a better logger late.
//            System.out.println(e.getMessage());
//            throw e;
//        }
//    }
//
//    public void login(UserDto userDto) throws Exception {
//        try {
//            Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
//            if (optionalUser.isEmpty() || !optionalUser.get().getPassword().equals(userDto.getPassword()))
//                throw new CustomError(401, errors.INVALID_CREDENTIALS);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            throw e;
//        }
//    }

    public User getUser(String id) {
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, errors.USER_NOT_FOUND);
            return optionalUser.get();
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errors.INTERNAL_SERVER_ERROR);
        }
    }
}
