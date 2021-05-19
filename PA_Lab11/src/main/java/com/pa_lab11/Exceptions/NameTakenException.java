package com.pa_lab11.Exceptions;

public class NameTakenException extends IllegalStateException {
    public NameTakenException() {
        super("Name Already Taken!");
    }
}
