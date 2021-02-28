package com.company;

/**
 * Represents a type of Source
 */
public class Factory extends Source{
    //Constructors
    /**
     * Constructs an object of type Factory, but without a given same, so we don't name it
     */
    public Factory(){
        super("\0","Factory");
    }
    /**
     * Constructs an object of type Factory, with a given name
     * @param name a given name for this Object
     */
    public Factory(String name) {
        super(name,"Factory");
    }
}