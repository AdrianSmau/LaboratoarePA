package com.company;

/**
 * Represents a type of Source
 */
public class Warehouse extends Source{
    //Constructors
    /**
     * Constructs an object of type Warehouse, but without a given same, so we don't name it
     */
    public Warehouse(){
        super("\0","Warehouse");
    }
    /**
     * Constructs an object of type Warehouse, with a given name
     * @param name a given name for this Object
     */
    public Warehouse(String name) {
        super(name,"Warehouse");
    }
}