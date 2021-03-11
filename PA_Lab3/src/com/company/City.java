package com.company;

import java.util.ArrayList;
import java.util.Collections;
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

    /**
     * Checks what Locations are Visitable, but not payable, and sorts them by their opening times
     */
    public void showVisiblenotPayable() {
        List<Location> searchedLocations = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            if ((nodes.get(i) instanceof Visitable) && !(nodes.get(i) instanceof Payable)) {
                searchedLocations.add(nodes.get(i));
            }
        }
        Collections.sort(searchedLocations, (Location o1, Location o2) -> {
            if (o1 instanceof Visitable && o2 instanceof Visitable)
                return ((Visitable) o1).getOpeningTime().compareTo(((Visitable) o2).getOpeningTime());
            else
                return 0;
        });
        System.out.println("The Visitable but not Payable Locations have been sorted by their Opening Time!...");
        for (int i = 0; i < searchedLocations.size(); i++) {
            System.out.print(searchedLocations.get(i).getName() + " - opens at: ");
            if (searchedLocations.get(i) instanceof Visitable)
                System.out.println(((Visitable) searchedLocations.get(i)).getOpeningTime());
        }
    }

}
