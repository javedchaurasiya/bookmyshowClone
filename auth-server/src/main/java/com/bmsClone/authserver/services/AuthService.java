package com.bmsClone.authserver.services;

import com.bmsClone.authserver.constants.errors;
import com.bmsClone.authserver.error.CustomError;
import com.bmsClone.authserver.jwtUtils.JwtUtil;
import com.bmsClone.authserver.models.User;
import com.bmsClone.authserver.models.dtoModels.JwtDto;
import com.bmsClone.authserver.models.dtoModels.UserDto;
import com.bmsClone.authserver.userRepository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public void signup(UserDto userDto) {
        try {
            //will add hashing later.
            userRepository.save(userDto.toUser());
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User Already Exists");
        } catch (Exception e) {
            //will add a better logger late.
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public JwtDto login(UserDto userDto) {
        try {
            Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
            if (optionalUser.isEmpty() || !optionalUser.get().getPassword().equals(userDto.getPassword()))
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, errors.INVALID_CREDENTIALS);
            return new JwtDto(jwtUtil.generateToken(optionalUser.get()));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
