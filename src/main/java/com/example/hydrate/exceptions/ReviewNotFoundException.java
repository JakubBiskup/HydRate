package com.example.hydrate.exceptions;

public class ReviewNotFoundException extends ObjectNotFoundException{
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
