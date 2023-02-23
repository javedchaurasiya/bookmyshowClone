package com.bmsClone.showtimeAndTheatreMicroservices.models;

import com.mongodb.lang.NonNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
public class Theatre {
    @MongoId
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private int capacity;
    public Theatre(){}
}
