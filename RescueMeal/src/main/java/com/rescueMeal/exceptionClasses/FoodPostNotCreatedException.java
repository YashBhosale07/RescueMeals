package com.rescueMeal.exceptionClasses;

public class FoodPostNotCreatedException extends RuntimeException{
    public FoodPostNotCreatedException(String message){
        super(message);
    }
}
