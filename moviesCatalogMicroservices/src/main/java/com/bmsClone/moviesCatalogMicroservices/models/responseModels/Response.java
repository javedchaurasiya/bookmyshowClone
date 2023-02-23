package com.bmsClone.moviesCatalogMicroservices.models.responseModels;

import lombok.Data;

@Data
public class Response {
    Boolean success;
    String message;

    public Response(Boolean success, String message) {
        this.message = message;
        this.success = success;
    }
}
