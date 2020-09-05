package com.nhatduy.parloweb.exception;

import com.nhatduy.parloweb.entity.StatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StatusResponse> handleUserNotFoundException(Exception exc){
        StatusResponse error = new StatusResponse(exc.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StatusResponse> handleBadRequestException(Exception exc){
        StatusResponse error = new StatusResponse(exc.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<StatusResponse> handleUnauthorizedException(Exception exc){
        StatusResponse error = new StatusResponse(exc.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }
}
