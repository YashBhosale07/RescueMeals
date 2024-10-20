package com.rescueMeal.exceptionHandling;

import com.rescueMeal.exceptionClasses.ErrorResponse;
import com.rescueMeal.exceptionClasses.NgoNotFoundException;
import com.rescueMeal.exceptionClasses.NgoNotRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionforNGO {


    @ExceptionHandler(NgoNotRegisteredException.class)
    public ResponseEntity<ErrorResponse>cannotRegisterNGO(NgoNotRegisteredException e) {
        com.rescueMeal.exceptionClasses.ErrorResponse errorResponse = new com.rescueMeal.exceptionClasses.ErrorResponse();
        errorResponse.setLocalDateTime(LocalDateTime.now());
        errorResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NgoNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleNGONotFound(NgoNotFoundException e){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setLocalDateTime(LocalDateTime.now());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDetails("NGO is not present");
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
