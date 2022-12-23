package com.unicomer.unicomerApp.infrastructure.config.exception.handler;

import com.unicomer.unicomerApp.infrastructure.client.model.Error;
import com.unicomer.unicomerApp.infrastructure.config.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ZONE_ID = "Chile/Continental";

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<Object> handleCustomException(CustomException ex){
        Error error = new Error(
                ex.getMessage(),
                HttpStatus.valueOf(ex.getStatus()),
                getTime()
        );
        return new ResponseEntity<>(error, error.getHttpStatus());
    }

    @ExceptionHandler(value = {ConnectException.class})
    public ResponseEntity<Object> handleConnectException(ConnectException ex){
        Error error = new Error(
                ex.getMessage(),
                HttpStatus.BAD_GATEWAY,
                getTime()
        );
        return new ResponseEntity<>(error, error.getHttpStatus());
    }

    private ZonedDateTime getTime(){
        return ZonedDateTime.now(ZoneId.of(ZONE_ID));
    }
}
