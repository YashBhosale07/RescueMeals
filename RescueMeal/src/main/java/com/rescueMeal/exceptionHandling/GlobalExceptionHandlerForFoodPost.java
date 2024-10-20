package com.rescueMeal.exceptionHandling;


import com.rescueMeal.exceptionClasses.ErrorResponse;
import com.rescueMeal.exceptionClasses.FoodPostNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandlerForFoodPost {

    @ExceptionHandler(FoodPostNotCreatedException.class)
    public ResponseEntity<ErrorResponse>handleFoodPostException(FoodPostNotCreatedException foodNotCreatedException){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setLocalDateTime(LocalDateTime.now());
        errorResponse.setMessage(foodNotCreatedException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
