package com.main;

import com.catalog.Catalog;
import com.catalog.CatalogUtil;
import com.exceptions.*;
import com.items.Item;
import com.items.myItems.Movie;
import com.items.myItems.Song;
import com.shell.Shell;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n ----- Compulsory ----- \n");
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
        app.testPrintFunction();
        System.out.println("\n ----- Optional ----- \n");
        try {
            Shell.runShell();
        } catch (ReadlineException ex) {
            System.err.println("Custom exception caught! Problem when trying to read line from buffer!...");
        } catch (InvalidPathException ex) {
            System.err.println("Custom exception caught! Problem when trying to specify path!...");
        } catch (EmptyCatalogException ex) {
            System.err.println("Custom exception caught! Problem when trying to access the local catalog!...");
        } catch (InvalidCommandException ex) {
            System.err.println("Custom exception caught! Invalid command detected!...");
        }
        System.out.println("\n ----- Bonus ----- \n");
        app.graphColoringBonus();

    }

    private void testCreateSave() {
        Catalog catalog = new Catalog("My Catalog", "C:\\Users\\adria\\Desktop\\My Items\\catalog.ser");
        var song = new Song("Best Song", "Smells Like Teen Spirit", "C:\\Users\\adria\\Desktop\\My Items\\Nirvana - Smells Like Teen Spirit.mp3", Arrays.asList("Kurt Cobain", "Nirvana"), 1991, true, URI.create("https://www.youtube.com/watch?v=hTWKbfoikeg"));
        var movie = new Movie("Best Movie", "Inception", "C:\\Users\\adria\\Desktop\\My Items\\Inception.mp4", "Christopher Nolan", "Leonardo DiCaprio", 8.8, 2010, URI.create("https://www.imdb.com/title/tt1375666/"));
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

    private void testPrintFunction() {
        Catalog catalog = new Catalog("My Catalog", "C:\\Users\\adria\\Desktop\\My Items\\catalog.ser");
        var song = new Song("Best Song", "Smells Like Teen Spirit", "C:\\Users\\adria\\Desktop\\My Items\\Nirvana - Smells Like Teen Spirit.mp3", Arrays.asList("Kurt Cobain", "Nirvana"), 1991, true, URI.create("https://www.youtube.com/watch?v=hTWKbfoikeg"));
        var movie = new Movie("Best Movie", "Inception", "C:\\Users\\adria\\Desktop\\My Items\\Inception.mp4", "Christopher Nolan", "Leonardo DiCaprio", 8.8, 2010, URI.create("https://www.imdb.com/title/tt1375666/"));
        CatalogUtil.add(catalog, song);
        CatalogUtil.add(catalog, movie);
        CatalogUtil.print(catalog);
    }

    private void graphColoringBonus() {

        //preparing the input
        Catalog graphCatalog = new Catalog("My Catalog", "C:\\Users\\adria\\Desktop\\My Items\\graphCatalog.ser");
        var song1 = new Song("Song1", "Smells Like Teen Spirit", "C:\\Users\\adria\\Desktop\\My Items\\Nirvana - Smells Like Teen Spirit.mp3", Arrays.asList("Kurt Cobain", "Nirvana"), 1991, true, URI.create("https://www.youtube.com/watch?v=hTWKbfoikeg"));
        var movie1 = new Movie("Movie1", "Inception", "C:\\Users\\adria\\Desktop\\My Items\\Inception.mp4", "Christopher Nolan", "Leonardo DiCaprio", 8.8, 2010, URI.create("https://www.imdb.com/title/tt1375666/"));
        var song2 = new Song("Song2", "Just the way you are", "C:\\Users\\adria\\Desktop\\My Items\\Bruno Mars - Just The Way You Are.mp3", Arrays.asList("Bruno Mars"), 2010, true, URI.create("https://www.youtube.com/watch?v=LjhCEhWiKXk"));
        var song3 = new Song("Song3", "Love the way you lie", "C:\\Users\\adria\\Desktop\\My Items\\Eminem - Love The Way You Lie ft. Rihanna.mp3", Arrays.asList("Eminem", "Rihanna"), 2010, true, URI.create("https://www.youtube.com/watch?v=uelHwf8o7_U"));
        var song4 = new Song("Song4", "Yeah!", "C:\\Users\\adria\\Desktop\\My Items\\Usher - Yeah! ft. Lil Jon, Ludacris.mp3", Arrays.asList("Usher", "Lil Jon", "Ludacris"), 2005, true, URI.create("https://www.youtube.com/watch?v=GxBSyx85Kp8"));
        var movie2 = new Movie("Movie2", "Harry Potter", "C:\\Users\\adria\\Desktop\\My Items\\Harry Potter.mp4", "Mike Newell", "Daniel Radcliffe", 7.7, 2005, URI.create("https://www.imdb.com/title/tt0330373/"));
        var movie3 = new Movie("Movie3", "Fight Club", "C:\\Users\\adria\\Desktop\\My Items\\Fight Club.mp4", "David Fincher", "Brad Pitt", 8.8, 1999, URI.create("https://www.imdb.com/title/tt0137523/?ref_=fn_al_tt_1"));
        var movie4 = new Movie("Movie4", "No Country For Old Men", "C:\\Users\\adria\\Desktop\\My Items\\No Country For Old Men.mp4", "Coen Brothers", "Javier Bardem", 8.1, 2007, URI.create("https://www.imdb.com/title/tt0477348/?ref_=nv_sr_srsg_0"));
        var song5 = new Song("Song5", "I want it that way", "C:\\Users\\adria\\Desktop\\My Items\\Backstreet Boys - I Want It That Way.mp3", Arrays.asList("Backstreet Boys"), 1999, true, URI.create("https://www.youtube.com/watch?v=4fndeDfaWCg"));

        CatalogUtil.add(graphCatalog, song1);
        CatalogUtil.add(graphCatalog, song2);
        CatalogUtil.add(graphCatalog, song3);
        CatalogUtil.add(graphCatalog, song4);
        CatalogUtil.add(graphCatalog, song5);
        CatalogUtil.add(graphCatalog, movie1);
        CatalogUtil.add(graphCatalog, movie2);
        CatalogUtil.add(graphCatalog, movie3);
        CatalogUtil.add(graphCatalog, movie4);

        Item[] graphItems = new Item[graphCatalog.getItems().size()];
        int counter = 0;
        for (Item currItem : graphCatalog.getItems()) {
            graphItems[counter] = currItem;
            counter++;
        }

        //linked lists with each node's linked nodes
        LinkedList<Item>[] adj = new LinkedList[graphItems.length];
        for (int i = 0; i < graphItems.length; i++) {
            adj[i] = new LinkedList<>();
        }

        //I chose to link the Items based on their release year (Movies and Songs alike)!!!

        for (int i = 0; i < graphItems.length - 1; i++) {
            for (int j = i; j < graphItems.length; j++) {
                int year1 = 0;
                int year2 = 0;
                if (graphItems[i] instanceof Movie) {
                    year1 = ((Movie) graphItems[i]).getReleaseYear();
                } else {
                    if (graphItems[i] instanceof Song) {
                        year1 = ((Song) graphItems[i]).getReleaseYear();
                    }
                }
                if (graphItems[j] instanceof Movie) {
                    year2 = ((Movie) graphItems[j]).getReleaseYear();
                } else {
                    if (graphItems[j] instanceof Song) {
                        year2 = ((Song) graphItems[j]).getReleaseYear();
                    }
                }
                if (year1 == year2) {
                    adj[i].add(graphItems[j]);
                    adj[j].add(graphItems[i]);
                }
            }
        }

        //Start of graph coloring algorithm, Greedy approach

        Map<Item, Integer> results = new HashMap<>();
        for (Item currItem : graphItems) {
            results.put(currItem, -1);
        }
        int currentColor = -1;

        for (int i = 0; i < graphItems.length; i++) {
            boolean keepColor = false;
            if (results.get(graphItems[i]) == -1) {
                keepColor = true;
                currentColor++;
                results.put(graphItems[i], currentColor);
            }
            for (Item nextItem : adj[i]) {
                if (results.get(nextItem) == -1) {
                    if (!keepColor)
                        currentColor++;
                    results.put(nextItem, currentColor);
                }
            }
        }
        //printing the result
        System.out.println(" ----- Printing each item's color!... ----- \n");
        for (Item item : graphItems) {
            System.out.println("Item: " + item.getName() + " has the color with number: " + results.get(item));
        }

        System.out.println("\n ----- Generating a plan in order to properly play Items!... ----- \n");

        //number of colored element of color "index"
        int totalColors = currentColor + 1;
        //we make a frequency vector to keep track of how many elements with each label there are
        int[] numberOfCertainColor = new int[totalColors];
        for (int item : results.values()) {
            numberOfCertainColor[item]++;
        }
        //vector to mark colors already used
        boolean[] markedColor = new boolean[totalColors];
        //vector to mark nodes already played
        boolean[] markedNodes = new boolean[graphItems.length];
        Arrays.fill(markedColor, false);

        int day = 1;
        //number of labeled items that have been exhausted
        int doneSets = 0;
        while (doneSets != totalColors) {
            System.out.println("Day number " + day + ":");
            for (int i = 0; i < graphItems.length; i++) {
                if (numberOfCertainColor[results.get(graphItems[i])] != 0 && !markedColor[results.get(graphItems[i])] && !markedNodes[i]) {
                    numberOfCertainColor[results.get(graphItems[i])]--;
                    if (numberOfCertainColor[results.get(graphItems[i])] == 0) {
                        doneSets++;
                    }
                    markedColor[results.get(graphItems[i])] = true;
                    markedNodes[i] = true;
                    System.out.println("\t> " + graphItems[i].getName() + ", with color: " + results.get(graphItems[i]));
                }
            }
            day++;
            Arrays.fill(markedColor, false);
        }
    }
}
