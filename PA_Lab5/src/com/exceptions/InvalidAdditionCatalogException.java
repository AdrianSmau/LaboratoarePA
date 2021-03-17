package com.exceptions;

import com.items.Item;

public class InvalidAdditionCatalogException extends RuntimeException {
    public InvalidAdditionCatalogException(Item item) {
        super("The item: " + item.getId() + " is not valid!...");
    }
}
