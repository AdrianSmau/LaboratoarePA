package com.shell.commands.mycommands;

import com.catalog.Catalog;
import com.items.Item;
import com.items.myItems.Movie;
import com.items.myItems.Song;
import com.shell.commands.Command;
import freemarker.core.ParseException;
import freemarker.template.*;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.*;

public class ReportCommand extends Command {
    final private Catalog toBeReported;

    public ReportCommand(String path, Catalog toBeReported) {
        super("Report", path);
        this.toBeReported = toBeReported;
    }

    public void reportCatalog() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(ReportCommand.class, "template");
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Map<String, Object> input = new HashMap<>();
        input.put("reportedCatalog", toBeReported);
        List<Movie> movies = new ArrayList<>();
        List<Song> songs = new ArrayList<>();
        for (Item item : toBeReported.getItems()) {
            if (item instanceof Movie) {
                movies.add((Movie) item);
            }
            if (item instanceof Song) {
                songs.add((Song) item);
            }
        }
        input.put("movies", movies);
        input.put("songs", songs);

        Template template = null;
        try {
            template = cfg.getTemplate("report.ftl");
        } catch (TemplateNotFoundException ex) {
            System.err.println("Template not found!...");
        } catch (MalformedTemplateNameException ex) {
            System.err.println("Malformed template name!...");
        } catch (ParseException ex) {
            System.err.println("Parse exception!...");
        } catch (IOException ex) {
            System.err.println("IO exception!...");
        }

        Writer htmlWriter = null;
        try {
            htmlWriter = new FileWriter("report.html");
        } catch (IOException ex) {
            System.err.println("IO exception caught! Couldn't instantiate the HTML Report Writer!...");
        }
        try {
            template.process(input, htmlWriter);
        } catch (TemplateException ex) {
            System.err.println("Template exception caught when trying to process the template!...");
        } catch (IOException ex) {
            System.err.println("IO exception caught when trying to process the template!...");
        }
        File htmlFile = new File("report.html");
        Desktop desktop = Desktop.getDesktop();
        if (htmlFile.exists()) {
            try {
                desktop.open(htmlFile);
            } catch (IOException ex) {
                System.err.println("IO exception caught when trying to open the generated HTML report!...");
            }
        }
    }
}
