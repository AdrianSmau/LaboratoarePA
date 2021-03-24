package com.exceptions;

import com.catalog.Catalog;

import java.io.IOException;

public class WriteObjectException extends IOException {
    public WriteObjectException(Catalog catalog) {
        super("The catalog: " + catalog.getName() + "couldn't be written!...");
    }
}
