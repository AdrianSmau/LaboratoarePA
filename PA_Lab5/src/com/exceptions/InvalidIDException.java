package com.exceptions;

public class InvalidIDException extends RuntimeException {
    public InvalidIDException(String id) {
        super("The ID: " + id + " is invalid!...");
    }
}
