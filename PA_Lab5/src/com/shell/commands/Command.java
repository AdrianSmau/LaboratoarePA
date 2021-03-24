package com.shell.commands;

public class Command {
    final private String name;
    private String currentPath;

    public Command(String name, String path) {
        this.currentPath = path;
        this.name = name;
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }
}
