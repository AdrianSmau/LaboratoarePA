package com.shell.commands.mycommands;

import com.catalog.Catalog;
import com.catalog.CatalogUtil;
import com.shell.commands.Command;

public class PlayCommand extends Command {
    private final Catalog toBeSearched;
    private final String idToBePlayed;

    public PlayCommand(String path, Catalog toBeSearched, String idToBePlayed) {
        super("Play", path);
        this.toBeSearched = toBeSearched;
        this.idToBePlayed = idToBePlayed;
    }

    public void playCatalog() {
        CatalogUtil.play(toBeSearched.findById(idToBePlayed));
    }
}
