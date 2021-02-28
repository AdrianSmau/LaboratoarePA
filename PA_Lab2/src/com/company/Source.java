package com.company;

/**
 * Represents a Source as a tuple of a name and a type
 * Is an abstract class, which has Warehouse and Factory as subclasses
 */
public abstract class Source {
    /**
     * Name of the Source
     */
    protected String name;
    /**
     * Type of the Source
     */
    protected String type;
    //Constructors

    /**
     * Instantiates a Source with a name and a type
     * @param name name for the Source
     * @param type type of the Source
     */
    public Source(String name,String type){
        this.name=name;
        this.type=type;
    }

    /**
     * Series of Getters and Setters
     */
    //GETTERS
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Overwrite of the toString method
     * @return all the information about this Source
     */
    //toString
    @Override
    public String toString() {
        return "Name of the Source is: " + name + " ,and the Type is: " + type + ".";
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
        if (!(obj instanceof Source)){
            return false;
        }
        Source other = (Source) obj;
        return (name.equals(other.name) && type.equals(other.type));
    }
}