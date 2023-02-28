package com.bmsClone.showtimeAndTheatreMicroservices.models.modelsDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class MovieDto {
    private String id;
    private String title;
    private int lengthInMinutes;
    private String castCrew;
    private Date releaseDate;

}
