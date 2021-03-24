package com.exceptions;

import com.catalog.Catalog;

import java.io.IOException;

public class ReadObjectException extends IOException {
    public ReadObjectException(Catalog catalog) {
        super("The catalog: " + catalog.getName() + "couldn't be written!...");
    }
}
