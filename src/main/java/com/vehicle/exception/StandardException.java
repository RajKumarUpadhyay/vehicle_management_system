package com.vehicle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.Objects;

@ControllerAdvice
public class StandardException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException runtimeException, WebRequest webRequest) {
        ApiError apiError = new ApiError();
        apiError.setErrorMessage(runtimeException.getMessage());
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest) {
        ApiError apiError = new ApiError();
        apiError.setErrorMessage(resourceNotFoundException.getMessage());
        apiError.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(SQLException sqlException, WebRequest webRequest) {
        ApiError apiError = new ApiError();
        apiError.setErrorMessage(sqlException.getMessage());
        apiError.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        return new ResponseEntity<>(apiError, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<Object> unrecognizedPropertyException(UnrecognizedPropertyException unrecognizedPropertyException, WebRequest webRequest) {
        ApiError apiError = new ApiError();
        apiError.setErrorMessage(unrecognizedPropertyException.getMessage());
       apiError.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
