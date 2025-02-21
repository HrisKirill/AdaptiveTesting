package com.khrystoforov.adaptivetesting.exception;


public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
