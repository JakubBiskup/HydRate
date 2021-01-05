package com.example.hydrate.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException exception){
        ApiError error= new ApiError(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(ZoneId.of("Z")));
        return  new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception){

        if(exception.getConstraintViolations()==null){
            ApiError error=new ApiError(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, ZonedDateTime.now(ZoneId.of("Z")));
            return new ResponseEntity<>(error,HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Set<ConstraintViolation<?>> constraintViolationSet =exception.getConstraintViolations();
        String errorMessage="Validation failed. Constraints violated:";
        for (ConstraintViolation<?> constraintViolation:
             constraintViolationSet) {
            errorMessage+=constraintViolation.getPropertyPath().toString()+" "+constraintViolation.getMessage()+".";

        }

        ApiError error=new ApiError(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(error,HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
