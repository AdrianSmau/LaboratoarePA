package com.pa_lab11.Exceptions;

public class UnexistentPersonException extends IllegalStateException {
    public UnexistentPersonException(){
        super("Person not found in DataBase!");
    }
}
