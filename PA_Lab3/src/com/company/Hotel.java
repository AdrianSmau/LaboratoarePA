package com.company;

import java.util.Map;

public class Hotel extends Location implements Payable, Classifiable {
    //compareTo override
    private double ticketPrice;
    private String classification;

    //Constructors
    public Hotel(String name, Map<Location, Integer> travelCost, double ticketPrice, String classification) {
        super(name, travelCost);
        this.ticketPrice = ticketPrice;
        this.classification = classification;
    }
    public Hotel() {
        super();
        this.ticketPrice = 0;
        this.classification = "Unknown";
    }

    //getTicketPrice override
    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }

    //Setter
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    //getClassification override
    @Override
    public String getClassification() {
        return classification;
    }

    //Setter
    public void setClassification(String classification) {
        this.classification = classification;
    }
}
