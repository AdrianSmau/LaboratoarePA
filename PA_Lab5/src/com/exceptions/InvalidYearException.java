package com.exceptions;

public class InvalidYearException extends RuntimeException {
    public InvalidYearException(int year) {
        super("The year: " + year + " is invalid!...");
    }
}
