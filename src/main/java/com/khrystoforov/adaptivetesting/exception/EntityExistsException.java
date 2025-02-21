package com.khrystoforov.adaptivetesting.exception;

public class EntityExistsException extends RuntimeException {
    public EntityExistsException(String message) {
        super(message);
    }
}
