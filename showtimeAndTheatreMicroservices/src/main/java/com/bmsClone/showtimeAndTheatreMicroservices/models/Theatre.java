package com.bmsClone.showtimeAndTheatreMicroservices.models;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Theatre {
    @MongoId
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private int capacity;
}
