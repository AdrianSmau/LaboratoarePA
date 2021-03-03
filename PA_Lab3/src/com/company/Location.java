package com.company;

import java.util.HashMap;
import java.util.Map;

public abstract class Location implements Comparable<Location> {
    private String name;
    private Map<Location, Integer> travelCost;

    //Constructors
    public Location(String name, Map<Location, Integer> travelCost) {
        this.name = name;
        this.travelCost = travelCost;
    }

    public Location() {
        this.name = "\0";
        this.travelCost = new HashMap<>();
    }

    //Getter
    public String getName() {
        return name;
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }

    //Getter
    public Map<Location, Integer> getTravelCost() {
        return travelCost;
    }

    //Setter
    public void setTravelCost(Map<Location, Integer> travelCost) {
        this.travelCost = travelCost;
    }

    public void addCost(Location node, int value) {
        travelCost.put(node, value);
    }

    //compareTo override
    @Override
    public int compareTo(Location other) {
        return (this.getName().compareTo(other.getName()));
    }

    //for the toString override
    public String showCosts() {
        var stringBuilder = new StringBuilder();
        int iterator = 0;
        for (Map.Entry<Location, Integer> entry : travelCost.entrySet()) {
            iterator++;
            stringBuilder.append(iterator);
            stringBuilder.append(" : ");
            stringBuilder.append(this.getName());
            stringBuilder.append(" -> ");
            stringBuilder.append(entry.getKey().getName());
            stringBuilder.append(" => ");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    //toString override
    @Override
    public String toString() {
        return "Name: " + this.name + "\nCosts: " + showCosts();
    }
}
