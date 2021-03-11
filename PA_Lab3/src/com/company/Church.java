package com.company;

import java.time.LocalTime;
import java.util.Map;

public class Church extends Location implements Visitable {
    private LocalTime openingTime;
    private LocalTime closingTime;

    //Constructors
    public Church(String name, Map<Location, Integer> travelCost, LocalTime openingTime, LocalTime closingTime) {
        super(name, travelCost);
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }
    public Church() {
        super();
        this.defaultMethodVisitableOpening();
        this.defaultMethodVisitableClosing();
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
}
