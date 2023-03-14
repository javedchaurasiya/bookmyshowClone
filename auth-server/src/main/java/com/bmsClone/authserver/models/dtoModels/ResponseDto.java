package com.bmsClone.authserver.models.dtoModels;

import lombok.Data;

@Data
public class ResponseDto {
    Boolean success;
    String message;

    public ResponseDto(Boolean success, String message) {
        this.message = message;
        this.success = success;
    }
}
