package com.main;

import com.catalog.Catalog;
import com.catalog.CatalogUtil;
import com.exceptions.InvalidPathException;
import com.exceptions.ReadObjectException;
import com.exceptions.WriteObjectException;
import com.items.myItems.Movie;
import com.items.myItems.Song;

import java.net.URI;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n ----- Compulsory ----- \n");
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
        app.testPrintFunction();
    }

    private void testCreateSave() {
        Catalog catalog = new Catalog("My Catalog", "C:\\Users\\adria\\Desktop\\My Items\\catalog.ser");
        var song = new Song("Best Song", "Smells Like Teen Spirit", "C:\\Users\\adria\\Desktop\\My Items\\Nirvana - Smells Like Teen Spirit.mp3", Arrays.asList("Kurt Cobain", "Nirvana"), 1991, true, URI.create("https://www.youtube.com/watch?v=hTWKbfoikeg"));
        var movie = new Movie("Best Movie", "Inception", "C:\\Users\\adria\\Desktop\\My Items\\Inception", "Christopher Nolan", "Leonardo DiCaprio", 8.8, 2010, URI.create("https://www.imdb.com/title/tt1375666/"));
        CatalogUtil.add(catalog, song);
        CatalogUtil.add(catalog, movie);
        try {
            CatalogUtil.save(catalog);
        } catch (WriteObjectException ex) {
            System.err.println("Custom exception caught! Error when trying to write!...");
        } catch (InvalidPathException ex) {
            System.err.println("Custom exception caught! Invalid specified path!...");
        }
    }

    private void testLoadView() {
        Catalog catalog = new Catalog();
        try {
            catalog = CatalogUtil.load("C:\\Users\\adria\\Desktop\\My Items\\catalog.ser");
        } catch (ReadObjectException ex) {
            System.err.println("Custom exception caught! Error when trying to read!...");
        } catch (InvalidPathException ex) {
            System.err.println("Custom exception caught! Invalid specified path!...");
        }
        CatalogUtil.play(catalog.findById("Best Song"));
    }

    private void testPrintFunction(){
        Catalog catalog = new Catalog("My Catalog", "C:\\Users\\adria\\Desktop\\My Items\\catalog.ser");
        var song = new Song("Best Song", "Smells Like Teen Spirit", "C:\\Users\\adria\\Desktop\\My Items\\Nirvana - Smells Like Teen Spirit.mp3", Arrays.asList("Kurt Cobain", "Nirvana"), 1991, true, URI.create("https://www.youtube.com/watch?v=hTWKbfoikeg"));
        var movie = new Movie("Best Movie", "Inception", "C:\\Users\\adria\\Desktop\\My Items\\Inception", "Christopher Nolan", "Leonardo DiCaprio", 8.8, 2010, URI.create("https://www.imdb.com/title/tt1375666/"));
        CatalogUtil.add(catalog, song);
        CatalogUtil.add(catalog, movie);
        CatalogUtil.print(catalog);
    }
}
