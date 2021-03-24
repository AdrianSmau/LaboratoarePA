package com.shell;

import com.catalog.Catalog;
import com.exceptions.EmptyCatalogException;
import com.exceptions.InvalidCommandException;
import com.exceptions.InvalidPathException;
import com.exceptions.ReadlineException;
import com.shell.commands.mycommands.*;

import java.io.*;

public class Shell {
    public static void runShell() throws ReadlineException, InvalidPathException, EmptyCatalogException, InvalidCommandException {
        String commandLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentDir = "C:\\Users\\adria\\Desktop\\My Items";
        System.out.println(" ----- Java Shell ----- \n\nCurrent directory: " + currentDir + "\n");
        System.out.println("You currently do not have a locally imported catalog! In order to import it, load it from somewhere!\n");
        Catalog userCatalog = new Catalog();
        boolean quit = false;
        boolean catalogOk = false;
        while (true) {
            if (quit)
                break;
            System.out.print("cmd>");
            try {
                commandLine = reader.readLine();
            } catch (IOException ok) {
                throw new ReadlineException();
            }
            String[] eachCommand = commandLine.split(" ");
            switch (eachCommand[0]) {
                case "info":
                    if (!catalogOk) {
                        System.out.println("You did not load or create a valid Catalog!...");
                        break;
                    }
                    if (userCatalog.getItems().size() == 0) {
                        throw new EmptyCatalogException(userCatalog);
                    }
                    InfoCommand newInfoCmd = new InfoCommand(currentDir);
                    try {
                        newInfoCmd.showMetaInfo();
                    } catch (FileNotFoundException ex) {
                        System.err.println("File not found exception caught when parsing the current path!...");
                    } finally {
                        System.out.println("Succes! Metadata about each item has been extracted and printed!...");
                    }
                    break;
                case "report":
                    if (!catalogOk) {
                        System.out.println("You did not load or create a valid Catalog!...");
                        break;
                    }
                    if (userCatalog.getItems().size() == 0) {
                        throw new EmptyCatalogException(userCatalog);
                    }
                    ReportCommand newRecordCmd = new ReportCommand(currentDir, userCatalog);
                    newRecordCmd.reportCatalog();
                    break;
                case "play":
                    if (!catalogOk) {
                        System.out.println("You did not load or create a valid Catalog!...");
                        break;
                    }
                    if (userCatalog.getItems().size() == 0) {
                        throw new EmptyCatalogException(userCatalog);
                    }
                    System.out.println("Please insert the ID of the item to be searched and played!...");
                    try {
                        commandLine = reader.readLine();
                    } catch (IOException ok) {
                        throw new ReadlineException();
                    }
                    if (userCatalog.findById(commandLine) == null) {
                        System.out.println("The given element can't be found in the local catalog!...");
                        break;
                    }
                    PlayCommand newPlayCmd = new PlayCommand(currentDir, userCatalog, commandLine);
                    newPlayCmd.playCatalog();
                    break;
                case "quit":
                    quit = true;
                    System.out.println("You will be redirected out of the Shell!...");
                    break;
                case "help":
                    System.out.println("add - adds an entry to the current catalog!...");
                    System.out.println("save - saves the current catalog in the current path!...");
                    System.out.println("load OR load [catalog-name] - loads the catalog from the current path locally!...");
                    System.out.println("quit - quits the shell!...");
                    System.out.println("print - prints the content of the local catalog!...");
                    System.out.println("play - searches an item from the local catalog and plays it!...");
                    System.out.println("cd .. OR cd [path-name] - change the local path");
                    System.out.println("report - generates and opens the local catalog HTML Report!...");
                    break;
                case "add":
                    if (!catalogOk) {
                        System.out.println("You did not load or create a valid Catalog!...");
                        break;
                    }
                    if (userCatalog.getName() == null) {
                        System.out.println("The local catalog is null! Please specify a name for it!");
                        try {
                            commandLine = reader.readLine();
                        } catch (IOException ok) {
                            throw new ReadlineException();
                        }
                        userCatalog = new Catalog(commandLine, currentDir);
                    }
                    System.out.println("Please specify the item's type (Movie / Song) !...");
                    try {
                        commandLine = reader.readLine();
                    } catch (IOException ok) {
                        throw new ReadlineException();
                    }
                    switch (commandLine) {
                        case "movie":
                        case "Movie":
                            System.out.println("Please specify information about the Movie!...");
                            System.out.println("The information will be separated by the identifier ';' !...");
                            System.out.println("You will need an ID, a name, the director, the lead actor/actress, its score, its release year and a IMDB link!...");
                            try {
                                commandLine = reader.readLine();
                            } catch (IOException ok) {
                                throw new ReadlineException();
                            }
                            if (commandLine.equals("quit")) {
                                quit = true;
                                break;
                            }
                            String[] movieSpecs = commandLine.split(";");
                            AddCommand newMovieCmd = new AddCommand(movieSpecs, currentDir, true, userCatalog);
                            userCatalog = newMovieCmd.addToCatalog();
                            break;
                        case "song":
                        case "Song":
                            System.out.println("Please specify information about the Song!...");
                            System.out.println("The information will be separated by the identifier ';' !...");
                            System.out.println("You will need an ID, a name, the artist, its release year, a bool value to signify that it is on Spotify or not, and a Youtube link!...");
                            try {
                                commandLine = reader.readLine();
                            } catch (IOException ok) {
                                throw new ReadlineException();
                            }
                            if (commandLine.equals("quit")) {
                                quit = true;
                                break;
                            }
                            String[] songSpecs = commandLine.split(";");
                            AddCommand newSongCmd = new AddCommand(songSpecs, currentDir, false, userCatalog);
                            userCatalog = newSongCmd.addToCatalog();
                            break;
                        default:
                            throw new InvalidCommandException(commandLine);
                    }
                    break;
                case "load":
                    if (eachCommand.length == 1) {
                        System.out.println("What is the name of your Catalog?");
                        try {
                            commandLine = reader.readLine();
                        } catch (IOException ok) {
                            throw new ReadlineException();
                        }
                    } else
                        commandLine = eachCommand[1];
                    LoadCommand newLoadCmd = new LoadCommand(currentDir, commandLine);
                    userCatalog = newLoadCmd.loadCatalog();
                    catalogOk = true;
                    System.out.println("Catalog locally imported!...");
                    break;
                case "save":
                    if (!catalogOk) {
                        System.out.println("No user catalog has been defined!...");
                        break;
                    }
                    if (userCatalog.getItems().size() == 0) {
                        throw new EmptyCatalogException(userCatalog);
                    }
                    System.out.println("Adding current Catalog!...");
                    SaveCommand newSaveCmd = new SaveCommand(currentDir, userCatalog);
                    newSaveCmd.saveCatalog();
                    break;
                case "print":
                    if (!catalogOk) {
                        System.out.println("No user catalog has been defined!...");
                        break;
                    }
                    if (userCatalog.getItems().size() == 0) {
                        throw new EmptyCatalogException(userCatalog);
                    }
                    PrintCommand newPrintCmd = new PrintCommand(currentDir, userCatalog);
                    newPrintCmd.printCatalog();
                    break;
                case "cd":
                    if (eachCommand.length == 1) {
                        System.out.println("Please insert valid path!...");
                        break;
                    }
                    if (eachCommand[1].equals("..")) {
                        System.out.println("Switching to parent directory!...");
                        File tempFile = new File(currentDir);
                        if (!(tempFile.exists()) || !(tempFile.isDirectory()))
                            throw new InvalidPathException(eachCommand[1]);
                        else
                            currentDir = tempFile.getParent();
                        System.out.println("Current directory: " + currentDir);
                        break;
                    } else {
                        File tempFile2 = new File(eachCommand[1]);
                        if (!(tempFile2.exists()) || !(tempFile2.isDirectory()))
                            throw new InvalidPathException(eachCommand[1]);
                        else {
                            System.out.println("Switching to '" + eachCommand[1] + "' directory!...");
                            currentDir = eachCommand[1];
                        }
                        break;
                    }
                default:
                    throw new InvalidCommandException(commandLine);
            }
        }
    }
}
