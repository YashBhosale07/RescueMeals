package com.rescueMeal.GlobalExceptionHandler;

import com.rescueMeal.exceptionClasses.ErrorResponse;
import com.rescueMeal.exceptionClasses.UserAlreadyPresentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionForLogin {
    @ExceptionHandler(UserAlreadyPresentException.class)
    public ResponseEntity<ErrorResponse>handleUserAlreadyPresentException(UserAlreadyPresentException userAlreadyPresentException){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setLocalDateTime(LocalDateTime.now());
        errorResponse.setMessage(userAlreadyPresentException.getMessage());
        errorResponse.setDetails("User is already present create a new login account");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
