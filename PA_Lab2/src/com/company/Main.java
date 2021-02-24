//Tema realizata de Smau Adrian-Constantin, anul 2 grupa B5
package com.company;

/**
 * @author Adrian Smau
 * The Compusory and Optional tasks have been implemented
 */
public class Main {
    public static void main(String[] args) {
        //We create a vector of 3 new sources
        Source[] exampleSources = new Source[3];
        //First one is a factory
        exampleSources[0] = new Factory();
        //We set its name
        exampleSources[0].setName("S1");
        //Using the getters, we display some info about this Source
        System.out.println("Name of the Source is: " + exampleSources[0].getName() + " ,and the Type is: " + exampleSources[0].getType() + ".");
        //Second element of the vector is a Warehouse, we create it using the constructor and setting its name
        exampleSources[1] = new Warehouse("S2");
        //We display info about it using the toString overload we did in the class
        System.out.println(exampleSources[1]);
        exampleSources[2] = new Warehouse();
        exampleSources[2].setName("S3");
        System.out.println(exampleSources[2]);
        //We declare a Destination object with the default constructor
        Destination d1 = new Destination();
        //We set its name using the setter
        d1.setName("D1");
        System.out.println(d1);
        //We declare a Destination object with the constructor defined by us, setting its name
        Destination d2 = new Destination("D2");
        //Using the getters, we display some info about the newly created object
        System.out.println("Name of this Destination is: " + d2.getName() + ".");
        Destination d3 = new Destination("D3");
        System.out.println(d3);
        //We create a Destination vector in which we put the 3 object previously created
        Destination[] exampleDestinations = {d1,d2,d3};
        //We create a vector of integers which represent the Supply values
        int[] exampleSupplies = {10,35,25};
        //We create a vector of integers which represent the Demands values
        int[] exampleDemands = {20,25,25};
        //We create a matrix of integers which represent the Cost values
        int[][] exampleCosts = {{2,3,1},{5,4,8},{5,6,8}};
        //We create a new instance of a problem with the constructor we defined inside the class, using the data we created earlier
        Problem exampleProblem = new Problem(exampleSources,exampleDestinations,exampleSupplies,exampleDemands,exampleCosts);
        //Because we overloaded the toString, we are able to display a custom message about the problem
        System.out.println(exampleProblem);
        //We create an instance of the object Algoritm, which, using the constructor we made, takes the Problem object as a parameter and memorizes it locally
        Algorithm exampleAlgorithm = new Algorithm(exampleProblem);
        //A method of the Algorithm class returns a Solution object. So, we create a new Solution object and we construct it with the return of that method from Algorithm
        Solution exampleSolution = exampleAlgorithm.solveGreedy();
        //The toString of Solution has been overwritten, so we are able to display a custom message
        System.out.println(exampleSolution);
    }
}