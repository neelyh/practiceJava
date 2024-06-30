package com.example.demo.exceptions;

public class SaleAlreadyExistsException extends RuntimeException {
    public SaleAlreadyExistsException(String message) {
        super(message);
    }
}
