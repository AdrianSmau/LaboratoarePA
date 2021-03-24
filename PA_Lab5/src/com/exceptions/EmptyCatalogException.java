package com.exceptions;

import com.catalog.Catalog;

public class EmptyCatalogException extends RuntimeException {
    public EmptyCatalogException(Catalog catalog) {
        super("The catalog: " + catalog.getName() + " is empty!...");
    }
}
