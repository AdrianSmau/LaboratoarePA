package main;

import MainFrame.MainFrame;

public class Main {

    public static void main(String[] args) {
        System.out.println(" ----- NOTICE ----- \n coord-x must be between 0 and 600, coord-y between 0-800 and radius between 20-45 \n  ----- COMMANDS ----- \n draw [shape] [coord_x],[coord_y],[radius],[color] - draws the selected shape at the given coordinates, with a give radius and a given color\n delete [coord-x],[coord-y] - deletes a shape from the given coordinates\n freehand - enables freehand mode\n reset - resets canvas\n exit - exit frame\n");
        new MainFrame().setVisible(true);
    }
}
