package com.company;

/**
 * Represents a problem that needs to be solved
 */
public class Problem {
    /**
     * The Sources of the problem
     */
    private Source[] sources;
    /**
     * The Destinations of the problem
     */
    private Destination[] destinations;

    /**
     * The list of supplies
     */
    private int[] supply;
    /**
     * The list of demands
     */
    private int[] demand;
    /**
     * The matrix of costs
     */
    private int[][] cost;

    /**
     * We need to validate the problem input - one of the conditions is that no two Sources are the same. Here, we validate this aspect
     * @param sourceList the list of Sources given as an input in the Constructor
     * @return boolean which reflect the correctness of the input
     */
    //Methods useful for validating the input for the constructor -- we verify that all our sources and destinations are distinct, and we check if the supply and the demand is equal
    public boolean checkSources(Source[] sourceList){
        for(int i=0;i<sourceList.length;i++){
            for(int j=0;j<sourceList.length;j++){
                if(i!=j){
                    if(sourceList[i].equals(sourceList[j])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * We need to validate the problem input - one of the conditions is that no two Destinations are the same. Here, we validate this aspect
     * @param destinationList the list of Sources given as an input in the Constructor
     * @return boolean which reflect the correctness of the input
     */
    public boolean checkDestinations(Destination[] destinationList){
        for(int i=0;i<destinationList.length;i++){
            for(int j=0;j<destinationList.length;j++){
                if(i!=j){
                    if(destinationList[i].equals(destinationList[j])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * With this method, we verify that the sum of all supplies is equal to the sum of all demands - another condition applied to the input
     * @param supplies the supplies list given to the constructor
     * @param demands the demands list given to the constructor
     * @return boolean which reflect the correctness of the input
     */
    public boolean checkEquality(int[] supplies, int[] demands){
        int supplySum=0;
        int demandsSum=0;
        for(int i=0;i<supplies.length;i++){
            supplySum+=supplies[i];
        }
        for(int i=0;i<demands.length;i++){
            demandsSum+=demands[i];
        }
        return (supplySum==demandsSum);
    }

    /**
     * We do not allow a Problem with no input, so this case is ruled out
     */
    //Constructors
    public Problem(){
        System.out.println("The default constructor for Problem is not allowed in this problem instance!");
        System.exit(-1);
    }
    /**
     * Constructor for a Problem. Inside, we validate the input - we check if all the conditions are met
     * @param sources the list of Sources
     * @param destinations the list of Destinations
     * @param supply the list of supplies
     * @param demand the list of demands
     * @param cost the matrix of costs
     */
    public Problem(Source[] sources, Destination[] destinations, int[] supply, int[] demand, int[][] cost) {
        if((sources.length != supply.length) || (destinations.length != demand.length) || (!checkSources(sources)) || (!checkDestinations(destinations)) || (!checkEquality(supply,demand))){
            System.out.println("Input-ul nu respecta cerintele problemei!");
            System.exit(-1);
        }
        else{
            this.sources = sources;
            this.destinations = destinations;
            this.supply = supply;
            this.demand = demand;
            this.cost = cost;
        }
    }

    /**
     * All the necessary Getters and Setters
     */
    //GETTERS
    public Source[] getSources() {
        return sources;
    }
    public Destination[] getDestinations() {
        return destinations;
    }
    public int[] getSupply() {
        return supply;
    }
    public int[] getDemand() {
        return demand;
    }
    public int[][] getCost() {
        return cost;
    }

    //SETTERS
    public void setSources(Source[] sources) {
        this.sources = sources;
    }
    public void setDestinations(Destination[] destinations) {
        this.destinations = destinations;
    }
    public void setSupply(int[] supply) {
        this.supply = supply;
    }
    public void setDemand(int[] demand) {
        this.demand = demand;
    }
    public void setCost(int[][] cost) {
        this.cost = cost;
    }

    //Methods helpful for the toString function

    /**
     * From the Sources of the Problem, we generate a String so that we can overload the toString method and display them nicely
     * @return the correspondent String for the Sources list
     */
    public String showSources(){
        String stringSources="\0";
        for(int i=0;i<this.sources.length;i++){
            stringSources+=this.sources[i].getName();
            stringSources+="-";
            stringSources+=this.sources[i].getType();
            stringSources+=" , ";
        }
        return stringSources;
    }
    /**
     * From the Destinations of the Problem, we generate a String so that we can overload the toString method and display them nicely
     * @return the correspondent String for the Destinations list
     */
    public String showDestinations(){
        String stringDestinations="\0";
        for(int i=0;i<this.destinations.length;i++){
            stringDestinations+=this.destinations[i].getName();
            stringDestinations+=" , ";
        }
        return stringDestinations;
    }
    /**
     * From the supplies of the Problem, we generate a String so that we can overload the toString method and display them nicely
     * @return the correspondent String for the supplies list
     */
    public String showSupplies(){
        String stringSupplies="\0";
        for(int i=0;i<this.supply.length;i++){
            stringSupplies+=this.supply[i];
            stringSupplies+=" , ";
        }
        return stringSupplies;
    }
    /**
     * From the demands of the Problem, we generate a String so that we can overload the toString method and display them nicely
     * @return the correspondent String for the demands list
     */
    public String showDemands(){
        String stringDemands="\0";
        for(int i=0;i<this.demand.length;i++){
            stringDemands+=this.demand[i];
            stringDemands+=" , ";
        }
        return stringDemands;
    }
    /**
     * From the costs of the Problem, we generate a String so that we can overload the toString method and display them nicely
     * @return the correspondent String for the costs matrix
     */
    public String showCosts(){
        String stringCosts="\0";
        for(int i=0;i<this.cost.length;i++){
            for(int j=0; j<this.cost.length;j++){
                stringCosts+=this.cost[i][j];
                stringCosts+=" ";
            }
            stringCosts+="/ ";
        }
        return stringCosts.substring(0,stringCosts.length()-3);
    }

    /**
     * Overwrite of the method toString
     * @return all the information about the Problem, displayed nicely using the methods stated above
     */
    //toString
    @Override
    public String toString() {
        return "Problem has the following Sources: " + showSources() + "the following Destinations: " + showDestinations() + "the following Supplies: " + showSupplies() + "the following Demands: " + showDemands() + "and the cost matrix is: " + showCosts() + ".";
    }
}