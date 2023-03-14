package com.bmsClone.authserver.models.dtoModels;

import com.bmsClone.authserver.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String email;
    private String phone;
    private String password;
    private Boolean admin;

    public User toUser() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .phone(phone)
                .admin(admin)
                .build();
    }
}
