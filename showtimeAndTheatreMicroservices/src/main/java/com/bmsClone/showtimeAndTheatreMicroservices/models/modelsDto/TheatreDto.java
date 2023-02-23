package com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto;

import com.bmsClone.showtimeAndTheatreMicroservices.models.Theatre;
import lombok.Data;

@Data
public class TheatreDto {
    private String name;
    private String address;
    private int capacity;

    public TheatreDto(){}

    public Theatre toTheatre(){
        Theatre theatre=new Theatre();
        theatre.setAddress(address);
        theatre.setName(name);
        theatre.setCapacity(capacity);
        return theatre;
    }
}
