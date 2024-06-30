package com.example.demo.exceptions;

public class MissingInformationException extends RuntimeException {
    public MissingInformationException(String message) {
        super(message);
    }
}