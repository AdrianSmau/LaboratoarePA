package com.shell.commands.mycommands;

import com.catalog.Catalog;
import com.catalog.CatalogUtil;
import com.shell.commands.Command;

public class PrintCommand extends Command {
    final private Catalog toBePrinted;

    public PrintCommand(String path, Catalog toBePrinted) {
        super("Print", path);
        this.toBePrinted = toBePrinted;
    }

    public void printCatalog() {
        CatalogUtil.print(toBePrinted);
    }
}
