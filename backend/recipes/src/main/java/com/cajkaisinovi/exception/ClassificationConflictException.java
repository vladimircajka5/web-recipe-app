package com.cajkaisinovi.recipes.exception;

public class ClassificationConflictException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClassificationConflictException(String message) {
        super(message);
    }
}