package com.company;

/**
 * Represents a class which solves a given problem using its methods
 */
public class Algorithm {
    /**
     * An object of type Problem, which describes the Transportation problem given
     */
    Problem problem;
    boolean[] rowDeleted;
    boolean[] colDeleted;

    /**
     * "Imports" a given Transportation problem locally, so than it can be solved
     *
     * @param problem a given Transportation problem
     */
    //Constructor
    public Algorithm(Problem problem) {
        this.problem = problem;
        rowDeleted = new boolean[problem.getSupply().length];
        colDeleted = new boolean[problem.getDemand().length];
    }

    /**
     * Solves the given problem using a Greedy method
     *
     * @return a newly created Solution object, constructed with the solution of the given problem as an integer
     */
    public Solution solveGreedy() {
        System.out.println("Greedy algorithm has begun on the given problem!");
        int totalCost = 0; // keeps track of the total cost generated at each step
        int toBeSubstracted = 0; // each time, we substract from the current Supply source and the chosen Demand destination, the minimum of these two is substracted from the supply as well as the demand
        int min = Integer.MAX_VALUE; // at each point, we will be searching for the minimum cost from the current level of Supply such that its supply is greater than 0. Here, we keep track of these costs from the Costs matrix of the problem
        int currentDestination = (-1); // when we find a cost which meets the demands (its supply is greater than 0 and its the minimum with this propriety), we memorize the Demand destination where we are going to send the supply to meet as much demand as possible
        for (int i = 0; i < problem.getSupply().length; i++) { // we iterate through the Supply sources, taking them in order
            while (problem.getSupply()[i] != 0) { // we send Supply to the Demand points which costs the minimum (in Greedy manner), so that we send as much as possible with the least cost from the current Supply until its Supply is 0. Then, we go on to the next Supply source
                min = Integer.MAX_VALUE; // we reset the minimum cost from the current level
                currentDestination = (-1); // we reset the Demand destination that holds the smallest cost in the Costs matrix
                for (int j = 0; j < problem.getDestinations().length; j++) { // we iterate through the current level of the Supply to find the least expensive "multiplyer"
                    if (problem.getCost()[i][j] <= min && problem.getDemand()[j] != 0) { // the conditions are : the cost from the matrix is as small as possible, and the Demand correspondent with that cost in relation to the current Supply has a demand that is greater than 0 (if it is 0, we have no reason to send there)
                        min = problem.getCost()[i][j];
                        currentDestination = j;
                    }
                }
                totalCost += min * Math.min(problem.getSupply()[i], problem.getDemand()[currentDestination]); // to the total cost, we add the product between the "multiplyer" from the Costs matrix and the minimum between the remaining supply of the current Supply level and the remaining demand of the chosen Demand point
                toBeSubstracted = Math.min(problem.getSupply()[i], problem.getDemand()[currentDestination]); // we keep this in a variable
                problem.getSupply()[i] -= toBeSubstracted; // from the current level of supply, we substract what we sent to the chosen Demand point
                problem.getDemand()[currentDestination] -= toBeSubstracted; // from the current level of demand, we substract what we received from the current Supply level
                System.out.println(problem.getSources()[i].getName() + " -> " + problem.getDestinations()[currentDestination].getName() + ": " + min + " cost * " + toBeSubstracted + " units => " + (min * toBeSubstracted) + " cost added to the Total!"); // For clarity issues, we display the current Supply source, the current Demand destination, the cost we found, the amount which is substracted from each supply and demand and the current total
                System.out.println("Current Total: " + totalCost);
            }
        }
        return new Solution(totalCost); // finally, we return the total cost of these operrations
    }

    // VOGEL'S APPROXIMATION ALGORITHM

    /**
     * This method takes a line (row / column) and computes its difference between the minimum cost within it and the second minimum. It returns the actual difference between these two, the value of the minimum and the position in the line of that minimum
     * @param currentIndex the index of the row / column we compute at the moment
     * @param oppositeDimension the number of rows if our line is a collumn, respectively the number of collumns if our line is a row, so that we know how much we should iterate in order to check all the costs from the matrix
     * @param isRow a boolean which tells us if the current line is a row or not. This is useful when we organize the return data, so that we can format it row first, collumn second
     * @return the difference mentioned above, the actual cost of the minimum value from the line and its parameter within the line
     */
    public int[] computeDifference(int currentIndex, int oppositeDimension, boolean isRow) {
        int min1 = Integer.MAX_VALUE; // variable to memorize the minimum from this line
        int min2 = Integer.MAX_VALUE; // variable to memorize the second minimum from this line
        int minPosition = -1; // variable to memorize the location of the minimum cost
        for (int i = 0; i < oppositeDimension; i++) { // we iterate through the line costs
            if (isRow) { // if the elements belongs to a deleted line, we skip it
                if (colDeleted[i]) {
                    continue;
                }
            } else {
                if (rowDeleted[i]) {
                    continue;
                }
            }
            int currentCellCost = isRow ? problem.getCost()[currentIndex][i] : problem.getCost()[i][currentIndex]; // we format the current cell's cost to that it respects the convention : row index first, collumn index second
            if (currentCellCost < min1) { // we update the 2 least costs from this line
                min2 = min1; // the minimum becomes the second minimum
                min1 = currentCellCost; // we memorize the new minimum cost
                minPosition = i; // we memorize the minimum cost's index
            } else if (currentCellCost < min2) { // or, if the element is not the minimum but is the second minimum
                min2 = currentCellCost;
            }
        }
        return new int[]{min2 - min1, min1, minPosition}; // we return the penalty of this line, the minimum element from this line and its position in the matrix
    }

    /**
     * This method searches for the maximum penalty from either all rows, or all collumns
     * @param dim1 the first dimension (the row dimension if we check the rows, the collumn one if we check the collumns)
     * @param dim2 the second dimension, the one which doesn't represent the lines we search
     * @param isRow a boolean which indicates if we search for the max penalty within all the lines or all the rows
     * @return the index of the row / collumn with the maximum penalty, alongside the index of the minimum cost element from within that line, and their values (the value of the penalty and the cost of the mentioned element from the matrix)
     */
    public int[] maxPenalty(int dim1, int dim2, boolean isRow) {
        /*if(isRow)
            System.out.println("We are searching for the max penalty in all rows!\n");
        else
            System.out.println("We are searching for the max penalty in all collumns!\n");
         */
        int maxDif = Integer.MIN_VALUE; // the maximum difference between the minimum and the second minimum costs from this specific line
        int maxDifLinePosition = -1; // the position of the current line
        int minCost = -1; // the minimum cost from this specific line
        int minCostCellPosition = -1; // the position of the cost cell within the line
        for (int i = 0; i < dim1; i++) { // we iterate through the current line
            if (isRow) { // we skip the collumns or the rows that have been deleted
                if (rowDeleted[i]) {
                    continue;
                }
            } else {
                if (colDeleted[i]) {
                    continue;
                }
            }
            int[] result = computeDifference(i, dim2, isRow); // for each line, we compute the difference mentioned above. This method returns the difference, alongside the minimum cost for this particular line and the position of this cost within the line
            if (result[0] > maxDif) { // we keep track of the maximum penalty overall, updating the maximum difference, the minimum cost and their positions
                maxDifLinePosition = i;
                maxDif = result[0];
                minCost = result[1];
                minCostCellPosition = result[2];
            }
        }
        return isRow ? new int[]{maxDifLinePosition, minCostCellPosition, minCost, maxDif} : new int[]{minCostCellPosition, maxDifLinePosition, minCost, maxDif}; // to respect the convention -> row index first, collumn index second. if our max penalty line is a row, we return its index first, then the minimum cost element index second, and if it is a collumn, the other way around
    }

    /**
     * This method finds the cell within the maximum penalty row / collumn, with the minimum cost in the cost matrix from all the other elements from that line
     * @return the index of the row / collumn, the index of the cell within that line. the value of the maximum penalty and the cost of the found cell
     */
    public int[] findNextCell() {
        //We search for the row/column that has the maximum difference between the minimum cost from that specific line and the second minimum cost.
        int[] rowValues = maxPenalty(problem.getSupply().length, problem.getDemand().length, true); // Firstly, we search for the maximum row difference. Within that row, we also get the cell with the minimum cost
        int[] colValues = maxPenalty(problem.getDemand().length, problem.getSupply().length, false); // We search for the same parameters, but for the collumns
        if (rowValues[3] == colValues[3]) { // the case in which the maximum difference from all the rows is equal to the max difference from all the collumns
            System.out.println("Both the row number " + rowValues[0] + " and the collumn number " + colValues[1] + " have the same maximum penalty. In this case, we select the line that has, within it, the least expensive cell!");
            if (rowValues[2] < colValues[2])
                System.out.println("Therefore, we select the row, which has the cell with the value " + rowValues[2] + "!\n");
            else
                System.out.println("Therefore, we select the collumn, which has the cell with the value " + colValues[2] + "!\n");
            return (rowValues[2] < colValues[2]) ? rowValues : colValues; // to minimize the cost, we take the row/column which has the minimum cost from within the line lower than the other
        }
        if(rowValues[3] > colValues[3])
            System.out.println("We select the collumn with the index " + colValues[1] + ", which has the minimum cell index " + colValues[0] + "!\n");
        else
            System.out.println("We select the row with the index " + rowValues[0] + ", which has the minimum cell index " + rowValues[1] + "!\n");
        return (rowValues[3] > colValues[3]) ? colValues : rowValues; // if we do not have such a problem, we select the row/column with the maximum penalty
    }

    /**
     * Vogel's approximation algorithm - for the bonus part
     * @return a newly created Solution object, constructed with the solution of the given problem as an integer
     */
    public Solution solveVogel() {
        long t0 = System.currentTimeMillis();
        System.out.println("\nVogel's approximation algorithm has begun on the given problem!\n");
        int supplyLeft = 0; // here, we keep track of the remaining supply available
        int totalCost = 0; // we keep track, also, of the total transportation cost, which makes up the sollution of the problem
        int step = 0; // we keep track of the total steps it takes to solve the problem
        for (int i = 0; i < problem.getSupply().length; i++) { // first, we calculate the total supply
            supplyLeft += problem.getSupply()[i];
        }
        while (supplyLeft > 0) { // while there is still supply left
            step++;
            System.out.println("We are now beginning the step number: " + step + "\n");
            int[] nextCell = findNextCell(); // this method will return the coordinates of the cell with the minimum cost from the matrix and within the line with the maximum penalty
            int cellRow = nextCell[0];
            int cellCol = nextCell[1];
            int transportedQuantity = Math.min(problem.getDemand()[cellCol], problem.getSupply()[cellRow]); // the quantity transported in this step is the minimum between the demand from our designated cell and its supply
            problem.getDemand()[cellCol] -= transportedQuantity; // we substract from the cell's demand this minimum
            if (problem.getDemand()[cellCol] == 0) { // if the demand becomes 0, we erase the cell's collumn
                colDeleted[cellCol] = true;
            }
            problem.getSupply()[cellRow] -= transportedQuantity; // we substract from the cell's supply this minimum
            if (problem.getSupply()[cellRow] == 0) { // if the supply becomes 0, we erase the cell's supply
                rowDeleted[cellRow] = true;
            }
            totalCost += transportedQuantity * problem.getCost()[cellRow][cellCol]; // the total cost becomes the sum of its previous value and this minimum between the supply and demnad, multiplied with the transportation cost of the selected cell
            supplyLeft -= transportedQuantity; // we substract from the remaining supply the amount which has been transported
        }
        System.out.println("We have finished the algorithm in " + step + " steps!");
        long t1 = System.currentTimeMillis();
        System.out.println("\nThe algorithm has taken " + (t1 - t0) + " millisecond(s)!\n");
        return new Solution(totalCost);
    }
}
