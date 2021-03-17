package com.exceptions;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException(String name) {
        super("The name: " + name + " is invalid!...");
    }
}
