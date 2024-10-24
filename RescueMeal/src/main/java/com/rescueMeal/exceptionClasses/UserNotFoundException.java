package com.rescueMeal.exceptionClasses;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException (String message){
        super(message);
    }

}
