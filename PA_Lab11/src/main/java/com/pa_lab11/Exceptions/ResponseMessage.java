package com.pa_lab11.Exceptions;

public class ResponseMessage {
    private String message;

    public ResponseMessage(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
