package com.unicomer.unicomerApp.infrastructure.config.exception;

public class CustomException extends RuntimeException{
    private int status;

    public CustomException(int status, String message) {
        super(message);
        this.status = status;
    }

    public CustomException(int status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
