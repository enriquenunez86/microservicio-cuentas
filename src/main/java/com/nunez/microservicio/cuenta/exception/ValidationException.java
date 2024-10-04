package com.nunez.microservicio.cuenta.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

}
