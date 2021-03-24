package com.shell.commands.mycommands;

import com.catalog.Catalog;
import com.catalog.CatalogUtil;
import com.exceptions.InvalidPathException;
import com.exceptions.WriteObjectException;
import com.shell.commands.Command;

public class SaveCommand extends Command {
    private final Catalog toBeWritten;

    public SaveCommand(String path, Catalog toBeWritten) {
        super("Save", path);
        this.toBeWritten = toBeWritten;
    }

    public void saveCatalog() {
        try {
            CatalogUtil.save(toBeWritten);
        } catch (WriteObjectException ex) {
            System.err.println("Custom exception caught! Error when trying to write!...");
        } catch (InvalidPathException ex) {
            System.err.println("Custom exception caught! Invalid specified path!...");
        }
    }
}
