package com.example.hydrate.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException exception){
        ObjectNotFoundExceptionObject exceptionObject= new ObjectNotFoundExceptionObject(exception.getMessage(),exception, HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("Z")));
        return  new ResponseEntity<>(exceptionObject,HttpStatus.NOT_FOUND);

    }
}
