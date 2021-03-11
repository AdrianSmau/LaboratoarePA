package com.company;

import java.time.LocalTime;
import java.util.Map;

public class Restaurant extends Location implements Payable, Visitable, Classifiable {
    private LocalTime openingTime;
    private LocalTime closingTime;
    private double ticketPrice;
    private String classification;

    //Constructors
    public Restaurant(String name, Map<Location, Integer> travelCost, LocalTime openingTime, LocalTime closingTime, double ticketPrice, String classification) {
        super(name, travelCost);
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.ticketPrice = ticketPrice;
        this.classification = classification;
    }
    public Restaurant() {
        super();
        this.defaultMethodVisitableOpening();
        this.defaultMethodVisitableClosing();
        this.ticketPrice = 0;
        this.classification = "Unknown";
    }

    //getOpeningTime override
    @Override
    public LocalTime getOpeningTime() {
        return openingTime;
    }

    //setOpeningTime override
    @Override
    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    //getClosingTime override
    @Override
    public LocalTime getClosingTime() {
        return closingTime;
    }

    //setClosingTime override
    @Override
    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
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
