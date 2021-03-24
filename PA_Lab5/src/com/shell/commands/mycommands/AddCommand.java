package com.shell.commands.mycommands;

import com.catalog.Catalog;
import com.catalog.CatalogUtil;
import com.items.myItems.Movie;
import com.items.myItems.Song;
import com.shell.commands.Command;

import java.net.URI;
import java.util.Arrays;

public class AddCommand extends Command {
    private final boolean isMovie;
    private final Catalog addHere;
    private final String[] args;

    public AddCommand(String[] args, String path, boolean isMovie, Catalog addHere) {
        super("Add", path);
        this.args = args;
        this.isMovie = isMovie;
        this.addHere = addHere;
    }

    public Catalog addToCatalog() {
        String tempPath = super.getCurrentPath() + "\\" + args[1];
        if (isMovie) {
            Movie toBeAdded = new Movie(args[0], args[1], tempPath, args[2], args[3], Double.parseDouble(args[4]), Integer.parseInt(args[5]), URI.create(args[6]));
            CatalogUtil.add(addHere, toBeAdded);
        } else {
            Song toBeAdded = new Song(args[0], args[1], tempPath, Arrays.asList(args[2]), Integer.parseInt(args[3]), Boolean.parseBoolean(args[4]), URI.create(args[5]));
            CatalogUtil.add(addHere, toBeAdded);
        }
        return addHere;
    }
}
