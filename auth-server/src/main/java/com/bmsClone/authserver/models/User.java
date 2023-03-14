package com.bmsClone.authserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Data
@Document(value = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @NonNull
    @Builder.Default
    private Boolean admin = false;

}
