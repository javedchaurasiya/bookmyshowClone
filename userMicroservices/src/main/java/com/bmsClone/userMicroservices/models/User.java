package com.bmsClone.userMicroservices.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.NonNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Data
@Document(value = "users")
public class User {
    @MongoId
    private String id;
    @NonNull
    private String name;
    @Indexed(unique = true)
    @NonNull
    private String email;
    private String phone;
    @NonNull
    @JsonIgnore
    private String password;

    public User(){}
}
