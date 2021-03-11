package com.company;

import java.time.LocalTime;
import java.util.Map;

public class Museum extends Location implements Visitable, Payable {

    private LocalTime openingTime;
    private LocalTime closingTime;
    private double ticketPrice;

    //Constructors
    public Museum() {
        super();
        this.defaultMethodVisitableOpening();
        this.defaultMethodVisitableClosing();
        this.ticketPrice = 0;
    }

    public Museum(String name, Map<Location, Integer> travelCost, LocalTime openingTime, LocalTime closingTime, double ticketPrice) {
        super(name, travelCost);
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.ticketPrice = ticketPrice;
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
}
