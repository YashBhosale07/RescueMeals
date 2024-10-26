package com.rescueMeal.exceptionClasses;

public class FoodPostNotFound extends RuntimeException {
    public FoodPostNotFound(String message) {
        super(message);
    }
}
