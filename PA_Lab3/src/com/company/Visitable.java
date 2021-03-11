package com.company;

import java.time.Duration;
import java.time.LocalTime;

public interface Visitable {
    static Duration getDuration(Location location) {
        if (location instanceof Visitable)
            return Duration.between(((Visitable) location).getOpeningTime(), ((Visitable) location).getClosingTime());
        else
            return Duration.ZERO;
    }

    LocalTime getOpeningTime();

    void setOpeningTime(LocalTime openingTime);

    LocalTime getClosingTime();

    void setClosingTime(LocalTime closingTime);

    default void defaultMethodVisitableOpening() {
        this.setOpeningTime(LocalTime.of(9, 30));
    }

    default void defaultMethodVisitableClosing() {
        this.setClosingTime(LocalTime.parse("20:00"));
    }
    // replaced every Visitable Location default constructor by calling these methods
}
