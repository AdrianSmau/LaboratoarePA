//proiect creat de Smau Adrian-Constantin, grupa 2B5, anul II
package com.company;

import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        Hotel v1 = new Hotel();
        v1.setName("V1");
        v1.setClassification("4 Stars");
        v1.setTicketPrice(90);

        Museum v2 = new Museum();
        v2.setName("V2");
        v2.setOpeningTime(LocalTime.of(9,30));
        v2.setClosingTime(LocalTime.parse("17:00"));
        v2.setTicketPrice(20);

        Museum v3 = new Museum();
        v3.setName("V3");
        v3.setOpeningTime(LocalTime.of(18,45));
        v3.setClosingTime(LocalTime.MIDNIGHT);
        v3.setTicketPrice(60);

        Church v4 = new Church();
        v4.setName("V4");
        v4.setOpeningTime(LocalTime.of(7,0));
        v4.setClosingTime(LocalTime.MIDNIGHT);

        Church v5 = new Church();
        v5.setName("V5");
        v5.setOpeningTime(LocalTime.parse("08:00"));
        v5.setClosingTime(LocalTime.of(12,30));

        Restaurant v6 = new Restaurant();
        v6.setName("V6");
        v6.setClassification("Chinese");
        v6.setOpeningTime(LocalTime.of(10,0));
        v6.setClosingTime(LocalTime.parse("23:00"));
        v6.setTicketPrice(50);

        v1.addCost(v2,10);
        v1.addCost(v3,50);
        v2.addCost(v3,20);
        v2.addCost(v4,20);
        v2.addCost(v5,10);
        v3.addCost(v4,20);
        v4.addCost(v5,30);
        v4.addCost(v6,10);
        v5.addCost(v6,20);

        City city = new City();
        city.addLocation(v1);
        city.addLocation(v2);
        city.addLocation(v3);
        city.addLocation(v4);
        city.addLocation(v5);
        city.addLocation(v6);

        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);
        System.out.println(v5);
        System.out.println(v6);

        System.out.println(city);

    }
}
