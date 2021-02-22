package com.vehicle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnrecognizedPropertyException extends RuntimeException{

    public UnrecognizedPropertyException(String errorMessage) {
        super(errorMessage);
    }

    public UnrecognizedPropertyException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}