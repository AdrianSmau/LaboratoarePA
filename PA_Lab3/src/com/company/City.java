package com.company;

import java.util.ArrayList;
import java.util.List;

public class City {
    private List<Location> nodes;

    //Constructors
    public City() {
        this.nodes = new ArrayList<>();
    }
    public City(List<Location> nodes) {
        this.nodes = nodes;
    }

    //Setter
    public List<Location> getNodes() {
        return nodes;
    }

    //Getter
    public void setNodes(List<Location> nodes) {
        this.nodes = nodes;
    }

    //for the toString override
    public String showLocations() {
        var stringBuilder = new StringBuilder();
        for (int i = 0; i < nodes.size(); i++) {
            stringBuilder.append(nodes.get(i).getName());
            stringBuilder.append(", ");
        }
        return stringBuilder.substring(0, stringBuilder.toString().length() - 2);
    }

    public void addLocation(Location node) {
        nodes.add(node);
    }

    //toString override
    @Override
    public String toString() {
        return "The whole city contains the following Locations: " + showLocations();
    }
}
