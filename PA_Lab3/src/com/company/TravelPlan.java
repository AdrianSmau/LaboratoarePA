package com.company;

import java.util.HashMap;
import java.util.Map;

public class TravelPlan {
    private City myCity;
    private Map<Location, Integer> myMap;
    private Location source;
    private Location destination;

    //Constructors
    public TravelPlan(City myCity, Map<Location, Integer> myMap, Location source, Location destination) {
        this.myCity = myCity;
        this.myMap = myMap;
        this.source = source;
        this.destination = destination;
    }

    public TravelPlan() {
        this.myCity = new City();
        this.myMap = new HashMap<>();
    }

    //Getter
    public City getMyCity() {
        return myCity;
    }

    //Setter
    public void setMyCity(City myCity) {
        this.myCity = myCity;
    }

    //Getter
    public Map<Location, Integer> getMyMap() {
        return myMap;
    }

    //Setter
    public void setMyMap(Map<Location, Integer> myMap) {
        this.myMap = myMap;
    }

    //Getter
    public Location getDestination() {
        return destination;
    }

    //Setter
    public void setDestination(Location destination) {
        this.destination = destination;
    }

    //Getter
    public Location getSource() {
        return source;
    }

    //Setter
    public void setSource(Location source) {
        this.source = source;
    }
}
