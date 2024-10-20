package com.rescueMeal.exceptionClasses;

public class NgoNotRegisteredException extends RuntimeException{
    public NgoNotRegisteredException(String message) {
        super(message);
    }
}
