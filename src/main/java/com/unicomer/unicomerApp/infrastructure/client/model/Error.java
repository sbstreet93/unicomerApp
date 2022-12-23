package com.unicomer.unicomerApp.infrastructure.client.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@ApiModel(value = "ErrorDto", description = "Object to map errors from Rick and Morty server")
public class Error {
    @ApiModelProperty("Error message")
    private final String message;
    @ApiModelProperty("Error status")
    private final HttpStatus httpStatus;
    @ApiModelProperty("Error time")
    private final ZonedDateTime timestamp;

    public Error(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
