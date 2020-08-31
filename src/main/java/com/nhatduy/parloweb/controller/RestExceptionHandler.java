package com.nhatduy.parloweb.controller;

import com.nhatduy.parloweb.entity.StatusResponse;
import com.nhatduy.parloweb.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    public RestExceptionHandler() {
    }

    @ExceptionHandler
    public ResponseEntity<StatusResponse> handleException(UserNotFoundException exc){
        StatusResponse error = new StatusResponse();
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StatusResponse> handleException(Exception exc){
        StatusResponse error = new StatusResponse();
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler
    public ResponseEntity<StatusResponse> handleException(BadCredentialsException exc){
        StatusResponse error = new StatusResponse();
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }
}
