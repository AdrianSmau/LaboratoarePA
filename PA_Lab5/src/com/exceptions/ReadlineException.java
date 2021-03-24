package com.exceptions;

import java.io.IOException;

public class ReadlineException extends IOException {
    public ReadlineException() {
        super("Problem occurred when attempting to get user input!...");
    }
}
