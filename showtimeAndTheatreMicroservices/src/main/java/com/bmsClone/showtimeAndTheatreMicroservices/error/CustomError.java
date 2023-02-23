package com.bmsClone.showtimeAndTheatreMicroservices.error;

import lombok.Data;

public class CustomError extends Exception {
    private int status;

    public CustomError(int status, String message) {
        super(message, new Throwable());
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
