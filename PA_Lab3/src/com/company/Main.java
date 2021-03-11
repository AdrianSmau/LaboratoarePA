//proiect creat de Smau Adrian-Constantin, grupa 2B5, anul II
package com.company;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Hotel v1 = new Hotel();
        v1.setName("V1");
        v1.setClassification("4 Stars");
        v1.setTicketPrice(90);

        Museum v2 = new Museum();
        v2.setName("V2");
        v2.setOpeningTime(LocalTime.of(9, 30));
        v2.setClosingTime(LocalTime.parse("17:00"));
        v2.setTicketPrice(20);

        Museum v3 = new Museum();
        v3.setName("V3");
        v3.setOpeningTime(LocalTime.of(18, 45));
        v3.setClosingTime(LocalTime.MIDNIGHT);
        v3.setTicketPrice(60);

        Church v4 = new Church();
        v4.setName("V4");
        v4.setOpeningTime(LocalTime.of(7, 0));
        v4.setClosingTime(LocalTime.MIDNIGHT);

        Church v5 = new Church();
        v5.setName("V5");
        v5.setOpeningTime(LocalTime.parse("08:00"));
        v5.setClosingTime(LocalTime.of(12, 30));

        Restaurant v6 = new Restaurant();
        v6.setName("V6");
        v6.setClassification("Chinese");
        v6.setOpeningTime(LocalTime.of(10, 0));
        v6.setClosingTime(LocalTime.parse("23:00"));
        v6.setTicketPrice(50);

        v1.addCost(v2, 10);
        v1.addCost(v3, 50);
        v2.addCost(v3, 20);
        v2.addCost(v4, 20);
        v2.addCost(v5, 10);
        v3.addCost(v2, 20);
        v3.addCost(v4, 20);
        v4.addCost(v5, 30);
        v4.addCost(v6, 10);
        v5.addCost(v4, 30);
        v5.addCost(v6, 20);

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

        System.out.println();
        System.out.println(city);
        System.out.println();

        city.showVisiblenotPayable();

        //static method
        System.out.println("The duration in which Church V5 is opened is: " + Visitable.getDuration(v5) + "!...\nThe duration in which the Museum V2 is opened is: " + Visitable.getDuration(v2) + "!...\n");

        System.out.println(" ----- DIJKSTRA ALGORITHM :: OPTIONAL PART ----- \n");

        //we will map the priorities using a map
        Map<Location, Integer> problemMap = new HashMap<>();
        problemMap.put(v2, 1);
        problemMap.put(v4, 7);
        problemMap.put(v3, 3);
        problemMap.put(v6, 4);
        problemMap.put(v5, 5);
        problemMap.put(v1, 6);
        // V1 -> V2 -> V4 -> V6 and V1 -> V2 -> V5 -> V6 are both minimum cost paths from V1 to V6
        //notice that we put V4 with a higher priority than V5, so the algorithm will choose the first path mentioned above
        TravelPlan myTravelPlan = new TravelPlan(city, problemMap, v1, v6);
        SolveTravelPlan myTravelPlanSolver = new SolveTravelPlan(myTravelPlan);
        myTravelPlanSolver.solveDijkstra();
        System.out.println("\n");
        //now, let's make a new instance of the problem and put V5 with a higher priority than V4, so the algorithm chooses the latter path mentioned above
        problemMap.clear();
        problemMap.put(v2, 1);
        problemMap.put(v4, 5);
        problemMap.put(v3, 3);
        problemMap.put(v6, 4);
        problemMap.put(v5, 7);
        problemMap.put(v1, 6);
        TravelPlan myTravelPlan2 = new TravelPlan(city, problemMap, v1, v6);
        SolveTravelPlan myTravelPlanSolver2 = new SolveTravelPlan(myTravelPlan2);
        myTravelPlanSolver2.solveDijkstra();

        System.out.println("\n\n ----- TSP ALGORITHM ATTEMPT :: BONUS PART ----- \n");

        //TSP
        v2.addCost(v1,10);
        v4.addCost(v2,20);
        v6.addCost(v4,10);
        v3.addCost(v1,50);
        v3.addCost(v5,10);
        City cityTSP = new City();
        List<Location> tspLocationsList = new ArrayList<>();
        tspLocationsList.add(v1);
        tspLocationsList.add(v2);
        tspLocationsList.add(v3);
        tspLocationsList.add(v4);
        tspLocationsList.add(v5);
        tspLocationsList.add(v6);
        cityTSP.setNodes(tspLocationsList);
        TravelPlan myTravelPlan3 = new TravelPlan(cityTSP, problemMap, v1, v6);
        SolveTravelPlan myTravelPlanSolver3 = new SolveTravelPlan(myTravelPlan3);
        myTravelPlanSolver3.solveTSP();
        //bonus not fully implemented...CSP attempted, but not finished
    }
}
