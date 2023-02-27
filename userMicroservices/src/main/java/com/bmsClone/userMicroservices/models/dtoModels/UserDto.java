package com.bmsClone.userMicroservices.models.dtoModels;

import com.bmsClone.userMicroservices.models.User;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String phone;
    private String password;

    public User toUser() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .phone(phone)
                .build();
    }
}
