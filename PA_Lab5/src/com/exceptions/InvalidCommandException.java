package com.exceptions;

import java.io.IOException;

public class InvalidCommandException extends IOException {
    public InvalidCommandException(String command){
        super("The command: " + command + " is not a valid one!...");
    }
}
