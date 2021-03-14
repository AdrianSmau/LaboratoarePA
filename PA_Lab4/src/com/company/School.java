package com.company;

public class School implements Comparable<School> {
    private String name;
    private int capacity;

    public School(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public School(String name) {
        this.name = name;
        this.capacity = 0;
    }

    @Override
    public int compareTo(School other) {
        return (this.getName().compareTo(other.getName()));
    }

    //name methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //capacity methods

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "School " + this.name;
    }
}
