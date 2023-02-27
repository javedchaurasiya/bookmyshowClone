package com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto;

import com.bmsClone.showtimeAndTheatreMicroservices.models.Theatre;
import lombok.Data;

@Data
public class TheatreDto {
    private String name;
    private String address;
    private int capacity;

    public TheatreDto() {
    }
    
    public Theatre toTheatre() {
        return Theatre.builder()
                .address(address)
                .name(name)
                .capacity(capacity)
                .build();
    }
}
