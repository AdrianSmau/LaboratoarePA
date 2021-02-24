package com.company;

/**
 * Represents a class which solves a given problem using its methods
 */
public class Algorithm {
    /**
     * An object of type Problem, which describes the Transportation problem given
     */
    Problem problem;

    /**
     * "Imports" a given Transportation problem locally, so than it can be solved
     * @param problem a given Transportation problem
     */
    //Constructor
    public Algorithm(Problem problem) {
        this.problem = problem;
    }

    /**
     * Solves the given problem using a Greedy method
     * @return greedySolution a newly created Solution object, constructed with the solution of the given problem as an integer
     */
    public Solution solveGreedy(){
        System.out.println("Greedy algorithm has begun on the given problem!");
        int totalCost = 0; // keeps track of the total cost generated at each step
        int toBeSubstracted = 0; // each time, we substract from the current Supply source and the chosen Demand destination, the minimum of these two is substracted from the supply as well as the demand
        int min = Integer.MAX_VALUE; // at each point, we will be searching for the minimum cost from the current level of Supply such that its supply is greater than 0. Here, we keep track of these costs from the Costs matrix of the problem
        int currentDestination = (-1); // when we find a cost which meets the demands (its supply is greater than 0 and its the minimum with this propriety), we memorize the Demand destination where we are going to send the supply to meet as much demand as possible
        for(int i=0;i< problem.getSupply().length;i++){ // we iterate through the Supply sources, taking them in order
            while(problem.getSupply()[i] != 0) { // we send Supply to the Demand points which costs the minimum (in Greedy manner), so that we send as much as possible with the least cost from the current Supply until its Supply is 0. Then, we go on to the next Supply source
                min = Integer.MAX_VALUE; // we reset the minimum cost from the current level
                currentDestination = (-1); // we reset the Demand destination that holds the smallest cost in the Costs matrix
                for (int j = 0; j < problem.getDestinations().length; j++) { // we iterate through the current level of the Supply to find the least expensive "multiplyer"
                    if (problem.getCost()[i][j] <= min && problem.getDemand()[j] != 0) { // the conditions are : the cost from the matrix is as small as possible, and the Demand correspondent with that cost in relation to the current Supply has a demand that is greater than 0 (if it is 0, we have no reason to send there)
                        min = problem.getCost()[i][j];
                        currentDestination = j;
                    }
                }
                totalCost += min * Math.min(problem.getSupply()[i],problem.getDemand()[currentDestination]); // to the total cost, we add the product between the "multiplyer" from the Costs matrix and the minimum between the remaining supply of the current Supply level and the remaining demand of the chosen Demand point
                toBeSubstracted = Math.min(problem.getSupply()[i],problem.getDemand()[currentDestination]); // we keep this in a variable
                problem.getSupply()[i] -= toBeSubstracted; // from the current level of supply, we substract what we sent to the chosen Demand point
                problem.getDemand()[currentDestination] -= toBeSubstracted; // from the current level of demand, we substract what we received from the current Supply level
                System.out.println(problem.getSources()[i].getName() + " -> " + problem.getDestinations()[currentDestination].getName() + ": " + min + " cost * " + toBeSubstracted + " units => " + (min*toBeSubstracted) + " cost added to the Total!"); // For clarity issues, we display the current Supply source, the current Demand destination, the cost we found, the amount which is substracted from each supply and demand and the current total
                System.out.println("Current Total: " + totalCost);
            }
        }
        Solution greedySolution = new Solution(totalCost);
        return greedySolution; // finally, we return the total cost of these operrations
    }
}
