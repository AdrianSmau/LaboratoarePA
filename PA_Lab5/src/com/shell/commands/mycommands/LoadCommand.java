package com.shell.commands.mycommands;

import com.catalog.Catalog;
import com.catalog.CatalogUtil;
import com.exceptions.InvalidPathException;
import com.exceptions.ReadObjectException;
import com.shell.commands.Command;

import java.io.File;

public class LoadCommand extends Command {
    private final String catalogName;

    public LoadCommand(String path, String catalogName) {
        super("Load", path);
        this.catalogName = catalogName;
    }

    public Catalog loadCatalog() {
        Catalog tempCatalog = new Catalog();
        var catalogPath = new StringBuilder(super.getCurrentPath() + "\\" + catalogName + ".ser");
        File catalogFile = new File(catalogPath.toString());
        if (!catalogFile.exists()) {
            System.out.println("The referred file does not exist!...");
            System.out.println("A null catalog has been imported locally!...");
            return tempCatalog;
        }
        System.out.println(catalogPath.toString());
        try {
            tempCatalog = CatalogUtil.load(catalogPath.toString());
        } catch (ReadObjectException ex) {
            System.err.println("Custom exception caught! Error when trying to read!...");
        } catch (InvalidPathException ex) {
            System.err.println("Custom exception caught! Invalid specified path!...");
        }
        return tempCatalog;
    }
}
