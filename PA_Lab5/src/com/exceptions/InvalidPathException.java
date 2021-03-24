package com.exceptions;

import java.io.FileNotFoundException;

public class InvalidPathException extends FileNotFoundException {
    public InvalidPathException(String path) {
        super("The path: " + path + " can't be found!...");
    }
}
