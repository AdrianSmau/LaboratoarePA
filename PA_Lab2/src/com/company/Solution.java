package com.company;

/**
 * Represents the solution of a problem which has gone through an algorithm
 * Created by a method of the class Algorithm, which solved a given Problem of Transportation and returns the solution as an integer - the cost
 */
public class Solution {
    /**
     * The solution of the Problem
     */
    private int finalCost;

    /**
     * Creates a new Solution without a given finalCost, so its value is set to 0
     * This finalCost must be an integer
     */
    //Constructors
    public Solution(){
        this(0);
    }
    /**
     * Creates a new Solution with a given finalCost
     * Prints a message, informing the user that the algorithm was ended succesfully
     * This finalCost must be an integer
     */
    public Solution(int finalCost) {
        System.out.println("The algorithm has worked succesfully!");
        this.finalCost = finalCost;
    }

    /**
     * Overwrite of the method toString
     * @return finalCost the Problem's solution
     */
    //toString
    @Override
    public String toString() {
        return "The found Solution is: " + finalCost + ".";
    }
}
