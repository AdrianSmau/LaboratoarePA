package com.shell.commands.mycommands;

import com.shell.commands.Command;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InfoCommand extends Command {

    public InfoCommand(String path) {
        super("Info", path);
    }

    public void parseFile(File myFile, FileInputStream myStream, BodyContentHandler myHandler, Metadata myMetadata, ParseContext myContext) {
        Parser parser = new AutoDetectParser();
        try {
            parser.parse(myStream, myHandler, myMetadata, myContext);
        } catch (IOException ex) {
            System.err.println("IO Exception encountered when trying to parse the element: " + myFile.toString() + "!...");
        } catch (SAXException ex) {
            System.err.println("SAX Exception encountered when trying to parse the element: " + myFile.toString() + "!...");
        } catch (TikaException ex) {
            System.err.println("Tika Exception encountered when trying to parse the element: " + myFile.toString() + "!...");
        }
        String[] metaInfos = myMetadata.names();
        for (String data : metaInfos) {
            System.out.println(data + ": " + myMetadata.get(data));
        }
    }

    public void showMetaInfo() throws FileNotFoundException {
        File parsedPath = new File(super.getCurrentPath());
        FileInputStream fis = null;
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        for (File currentFile : parsedPath.listFiles()) {
            if (currentFile == null) {
                throw new FileNotFoundException();
            }
            if (!currentFile.isDirectory() && !currentFile.getName().equals("catalog.ser")) {
                try {
                    fis = new FileInputStream(currentFile);
                } catch (FileNotFoundException ex) {
                    System.err.println("File to be parsed not found!...");
                }
                System.out.println("ITEM FOUND! - " + currentFile.getName() + "\n");
                parseFile(currentFile, fis, handler, metadata, context);
                System.out.print("\n");
            }
        }
    }
}
