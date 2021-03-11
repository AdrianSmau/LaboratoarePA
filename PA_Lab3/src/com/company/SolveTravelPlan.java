package com.company;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.extension.Tuples;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.selectors.values.IntDomainMin;
import org.chocosolver.solver.search.strategy.selectors.variables.MaxRegret;
import org.chocosolver.solver.variables.IntVar;

import java.util.Stack;

public class SolveTravelPlan {
    TravelPlan myPlan;
    Boolean[] hasBeenFinished; // memorizes if the node has been already calculated (the minimum distance from the source to it)
    Integer[] distanceTo; // memorizes the appropriate distance from the source to the node with the current index
    Integer totalLocationNumber; // total number of nodes
    Integer[] parents; // Vector to store the parent of the node with i-th index in the shortest path
    Integer[] pathPriority; // Vector similar in purpose with distanceTo, but for priorities, so that we can choose the path with the higher priority

    public SolveTravelPlan(TravelPlan myPlan) {
        this.myPlan = myPlan;
        hasBeenFinished = new Boolean[myPlan.getMyMap().size()];
        distanceTo = new Integer[myPlan.getMyMap().size()];
        parents = new Integer[myPlan.getMyMap().size()];
        pathPriority = new Integer[myPlan.getMyMap().size()];
        totalLocationNumber = myPlan.getMyCity().getNodes().size();
    }

    //Getter
    public TravelPlan getMyPlan() {
        return myPlan;
    }

    //Setter
    public void setMyPlan(TravelPlan myPlan) {
        this.myPlan = myPlan;
    }

    //Solver

    /**
     * generates the matrix based on the TravelPlan we received
     * -1 means no road between location 1 to location 2
     * 0 marks the same location in the matrix
     * a cost other than 0 or -1 means an actual road
     * @return them matrix
     */
    public int[][] generateProblemMatrix() {
        int[][] tspMatrix = new int[totalLocationNumber][totalLocationNumber];
        for (int i = 0; i < myPlan.getMyCity().getNodes().size(); i++) {
            for (int j = 0; j < myPlan.getMyCity().getNodes().size(); j++) {
                if (i == j)
                    tspMatrix[i][j] = 0;
                else {
                    if (myPlan.getMyCity().getNodes().get(i).getTravelCost().containsKey(myPlan.getMyCity().getNodes().get(j)))
                        tspMatrix[i][j] = myPlan.getMyCity().getNodes().get(i).getTravelCost().get(myPlan.getMyCity().getNodes().get(j));
                    else
                        tspMatrix[i][j] = -1;
                }
            }
        }
        return tspMatrix;
    }

    /**
     * verifies if the source is reachable
     * @param matrix the matrix of the TravelPlan
     * @return true or false
     */
    public boolean validateInputSource(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][myPlan.getMyCity().getNodes().indexOf(myPlan.getSource())] > 0)
                return true;
        }
        System.out.println("Input-ul oferit nu respecta cerintele!...");
        return false;
    }
    /**
     * verifies if the destiantion is reachable
     * @param matrix the matrix of the TravelPlan
     * @return true or false
     */
    public boolean validateInputDestination(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][myPlan.getMyCity().getNodes().indexOf(myPlan.getDestination())] > 0)
                return true;
        }
        System.out.println("Input-ul oferit nu respecta cerintele!...");
        return false;
    }

    /**
     * Helpful method which selects the next node to be processed. Iterates through all the unmarked nodes and picks the one that has the minimum processed (up until this point) distance from the source
     * If two nodes have the same distance, we pick the one with the higher priority in the user-given order
     *
     * @param pathPriority    the vector of priorities from the source to every node
     * @param distanceTo      the vector of distances from the source to every node
     * @param hasBeenFinished the vector that marks if the i-th node has been fully processed
     * @return the index of the chosen node
     */
    public Integer minDistanceMethod(Integer[] distanceTo, Boolean[] hasBeenFinished, Integer[] pathPriority) {
        Integer minDistance = Integer.MAX_VALUE;
        Integer minDistanceIndex = -1;
        for (int j = 0; j < totalLocationNumber; j++) {
            if (!hasBeenFinished[j] && distanceTo[j] < minDistance) {
                minDistance = distanceTo[j];
                minDistanceIndex = j;
            } else {
                if (!hasBeenFinished[j] && distanceTo[j] == minDistance) {
                    if (pathPriority[j] > pathPriority[minDistanceIndex]) {
                        // if the priority of the found node is higher than the one already selected as minimum, we change the index
                        minDistanceIndex = j;
                    }
                }
            }
        }
        return minDistanceIndex;
    }

    /**
     * This method computes the most efficient road from the source to every other node, while taking into account priorities
     * Returns the destination's distance from the source
     */
    public void solveDijkstra() {
        if (!validateInputDestination(generateProblemMatrix()))
            System.exit(-1);
        for (int i = 0; i < myPlan.getMyMap().size(); i++) { // initialize the vectors
            distanceTo[i] = Integer.MAX_VALUE;
            pathPriority[i] = Integer.MIN_VALUE;
            hasBeenFinished[i] = false;
        }
        for (int i = 0; i < totalLocationNumber; i++) { // the source has distance 0
            if (myPlan.getSource() == myPlan.getMyCity().getNodes().get(i)) {
                pathPriority[i] = myPlan.getMyMap().get(myPlan.getSource());
                distanceTo[i] = 0;
                parents[i] = -1;
            }
        }
        //Iterate through all the nodes-1, each time selecting the appropriate unmarked one
        for (int i = 0; i < totalLocationNumber - 1; i++) {
            Integer currentElem = minDistanceMethod(distanceTo, hasBeenFinished, pathPriority);
            hasBeenFinished[currentElem] = true; //we mark it
            //Furthermore, we update the distances of the nodes adjacent with the current one
            for (int j = 0; j < totalLocationNumber; j++) {
                //update distanceTo[j] only if:
                //1. j hasn't been marked yet
                //2. there is an edge from the current element to j
                //3. if the distance from the source to the already computed current element is smaller than the current distance from j to the source
                if (!hasBeenFinished[j] && myPlan.getMyCity().getNodes().get(currentElem).getTravelCost().containsKey(myPlan.getMyCity().getNodes().get(j)) && distanceTo[currentElem] + myPlan.getMyCity().getNodes().get(currentElem).getTravelCost().get(myPlan.getMyCity().getNodes().get(j)) < distanceTo[j]) {
                    pathPriority[j] = pathPriority[currentElem] + myPlan.getMyMap().get(myPlan.getMyCity().getNodes().get(j));
                    parents[j] = currentElem;
                    distanceTo[j] = distanceTo[currentElem] + myPlan.getMyCity().getNodes().get(currentElem).getTravelCost().get(myPlan.getMyCity().getNodes().get(j));
                } else { // if the distances are equal, we sort the parents by their priorities from the source to this point
                    //pathPriority works similar to distanceTo, but the update takes place if the priority is higher than the previous one
                    if (!hasBeenFinished[j] && myPlan.getMyCity().getNodes().get(currentElem).getTravelCost().containsKey(myPlan.getMyCity().getNodes().get(j)) && distanceTo[currentElem] + myPlan.getMyCity().getNodes().get(currentElem).getTravelCost().get(myPlan.getMyCity().getNodes().get(j)) == distanceTo[j]) {
                        if (pathPriority[j] < pathPriority[currentElem] + myPlan.getMyMap().get(myPlan.getMyCity().getNodes().get(j)))
                            parents[j] = currentElem;
                        pathPriority[j] = pathPriority[currentElem] + myPlan.getMyMap().get(myPlan.getMyCity().getNodes().get(j));
                    }
                }
            }
        }
        for (int i = 0; i < totalLocationNumber; i++) {
            if (myPlan.getDestination() == myPlan.getMyCity().getNodes().get(i)) {
                System.out.println("The distance from the source " + this.myPlan.getSource().getName() + " and the destination " + this.myPlan.getDestination().getName() + " is: " + distanceTo[i] + "!...");
                Stack<Integer> myStack = new Stack<>(); // used for showing the chosen path
                int parentIndex = i;
                myStack.push(parentIndex);
                while (parentIndex != 0) {
                    myStack.push(parents[parentIndex]);
                    parentIndex = parents[parentIndex];
                }
                myStack.pop();
                System.out.println("The path chosen by the algorithm is: ");
                System.out.print(this.myPlan.getSource().getName());
                while (!myStack.empty()) {
                    System.out.print(" -> " + myPlan.getMyCity().getNodes().get(myStack.pop()).getName());
                }
            }
        }
    }


    /**
     * an attempt to solve using the Choco Solver
     * managed to mark every node the algorithm passes, but the subcircuit doesnt accept any other length than 6, even though i put the lower bound on 3 and the upper one on 6...
     */
    public void solveTSP() {
        int[][] tspMatrix = generateProblemMatrix();
        if (!validateInputSource(tspMatrix) || !validateInputDestination(tspMatrix))
            System.exit(-1);
        totalLocationNumber = myPlan.getMyCity().getNodes().size();
        Model tspModel = new Model("TSP");
        IntVar limit = tspModel.intVar(120);
        IntVar[] succ = tspModel.intVarArray("succ", totalLocationNumber, 0, totalLocationNumber);
        IntVar[] dist = tspModel.intVarArray("dist", totalLocationNumber, 0, 60);
        //int[] viz;
        IntVar totDist = tspModel.intVar("totDist",0,50*totalLocationNumber);
        IntVar[] viz = tspModel.intVarArray("viz", totalLocationNumber, 0, 1);
        IntVar nrViz = tspModel.intVar("nrViz", 0, totalLocationNumber);
        //IntVar sumVizOverall = tspModel.intVar("sumVizOverall", 0, totalLocationNumber * totalLocationNumber);
        IntVar circuitSize = tspModel.intVar("circuitSize", 3, 6);
        for (int i = 0; i < totalLocationNumber; i++) {
            Tuples tspTuples = new Tuples(true);
            for (int j = 0; j < totalLocationNumber; j++) {
                if (tspMatrix[i][j] > 0)
                    tspTuples.add(j, tspMatrix[i][j]);
            }
            tspModel.table(succ[i], dist[i], tspTuples).post();
        }
        Tuples tspTuples2 = new Tuples(true);
        for (int i = 0; i < totalLocationNumber; i++) {
            if (succ[i] != tspModel.intVar(i))
                tspTuples2.add(i, 1);
            else
                tspTuples2.add(i, 0);
            tspModel.table(tspModel.intVar(i), viz[i], tspTuples2).post();
        }
        tspModel.subCircuit(succ, 0, circuitSize).post();
        tspModel.sum(viz, "=", nrViz).post();
        tspModel.sum(dist,"=", totDist).post();
        tspModel.sum(dist, "<=", limit).post();

        tspModel.setObjective(Model.MAXIMIZE, nrViz);
        Solver tspSolver = tspModel.getSolver();
        tspSolver.setSearch(
                Search.intVarSearch(
                        new MaxRegret(),
                        new IntDomainMin(),
                        dist
                )
        );
        tspSolver.showShortStatistics();
        while (tspSolver.solve()) {
            int current = 0;
            System.out.printf("v%d", current + 1);
            for (int j = 0; j < totalLocationNumber; j++) {
                System.out.printf("-> v%d", succ[current].getValue() + 1);
                current = succ[current].getValue();
            }
            System.out.printf("\nTotal visited locations : %d\n", nrViz.getValue());
            System.out.printf("Total minutes taken: %d\n",totDist.getValue());
        }
    }
}
