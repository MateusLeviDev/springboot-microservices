package com.levi.java.backend.controller.exception;

public class BusinessError extends RuntimeException {
    public BusinessError(String message) {
        super(message);
    }
}
