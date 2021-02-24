package com.company;

/**
 * Represents a Destination, which only has a name
 */
public class Destination {
    /**
     * The name of this Destination
     */
    private String name;

    //Constructors

    /**
     * Constructor for making a new Destination with a given name
     * @param name the name given to this Destination object
     */
    public Destination(String name){
        this.name=name;
    }

    /**
     * Constructor for making a new Destination object, but without a name, so we don't attribute it any name
     */
    public Destination(){
        this("\0");
    }

    /**
     * List of Setters and Getters
     */
    //GETTER
    public String getName() {
        return name;
    }

    //SETTER
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Overwrite of the toString method
     * @return all the information about this Destination
     */
    //toString
    @Override
    public String toString() {
        return "Name of this Destination is: " + name + ".";
    }

    /**
     * Overwrite of the equals method
     * @param obj another Object of unknown nature
     * @return boolean which reflects the equality of this object and the one received as a parameter
     */
    //equals
    @Override
    public boolean equals(Object obj){
        if(obj==null) {
            return false;
        }
        if (!(obj instanceof Destination)){
            return false;
        }
        Destination other = (Destination) obj;
        return name.equals(other.name);
    }
}