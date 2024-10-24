package com.rescueMeal.exceptionClasses;

public class UserAlreadyPresentException extends RuntimeException{
    public UserAlreadyPresentException(String message) {
        super(message);
    }
}
