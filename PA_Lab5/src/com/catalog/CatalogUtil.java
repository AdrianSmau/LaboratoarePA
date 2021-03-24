package com.catalog;

import com.exceptions.*;
import com.items.Item;
import com.items.myItems.Movie;
import com.items.myItems.Song;

import java.awt.*;
import java.io.*;

public class CatalogUtil {
    public static void save(Catalog catalog) throws WriteObjectException, InvalidPathException {
        try (FileOutputStream fos = new FileOutputStream(catalog.getPath())) {
            var out = new ObjectOutputStream(fos);
            out.writeObject(catalog);
            out.flush();
        } catch (FileNotFoundException ex) {
            throw new InvalidPathException(catalog.getPath());
        } catch (IOException ex) {
            throw new WriteObjectException(catalog);
        } finally {
            System.out.println("Writing process successfully terminated!...\n");
        }
    }

    public static Catalog load(String path) throws ReadObjectException, InvalidPathException {
        Catalog returnCatalog = new Catalog();
        try (FileInputStream fis = new FileInputStream(path)) {
            var in = new ObjectInputStream(fis);
            returnCatalog = (Catalog) in.readObject();
            return returnCatalog;
        } catch (FileNotFoundException ex) {
            throw new InvalidPathException(path);
        } catch (ClassNotFoundException ex) {
            System.err.println("The class Catalog can't be found!...");
        } catch (IOException ex) {
            throw new ReadObjectException(returnCatalog);
        } finally {
            System.out.println("Reading process successfully terminated!...\n");
        }
        return returnCatalog;
    }

    public static void print(Catalog catalog) throws EmptyCatalogException {
        if (catalog.getItems().size() == 0)
            throw new EmptyCatalogException(catalog);
        for (Item currItem : catalog.getItems()) {
            if (currItem instanceof Movie) {
                System.out.println(currItem);
            }
            if (currItem instanceof Song) {
                System.out.println(currItem);
            }
            System.out.print("\n");
        }
    }

    public static void add(Catalog catalog, Item item) throws InvalidAdditionCatalogException {
        if (item.getId() == null || item.getId().trim().equals("") || item.getName() == null || item.getName().trim().equals("") || item.getLocation() == null || item.getLocation().trim().equals(""))
            throw new InvalidAdditionCatalogException(item);
        catalog.getItems().add(item);
    }

    public static void play(Item item) {
        Desktop desktop = Desktop.getDesktop();
        try {
            if (item instanceof Movie) {
                desktop.browse(((Movie) item).getImdbLink());
            }
            if (item instanceof Song) {
                desktop.browse(((Song) item).getYoutubeLink());
            }
        } catch (IOException ex) {
            System.err.println("Having problems when browsing the given URI!...");
        } finally {
            System.out.println("Accessing the given URI!...\n");
        }
    }
}
