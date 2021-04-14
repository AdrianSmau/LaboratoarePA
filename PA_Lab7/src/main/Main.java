package main;

import Board.Board;
import Graphics.MainFrame;
import Graphics.Node;
import Players.Player;
import TimeKeeper.TimeKeeper;

import java.util.*;

public class Main {

    public static Map<Player, Integer> sortLeaderboard(Map<Player, Integer> myMap) {
        List<Map.Entry<Player, Integer>> myList = new LinkedList<>(myMap.entrySet());
        Comparator<Map.Entry<Player, Integer>> comparator = Comparator.comparing(Map.Entry<Player, Integer>::getValue);
        myList.sort(comparator.reversed());
        HashMap<Player, Integer> tempMap = new LinkedHashMap<>();
        for (Map.Entry<Player, Integer> x : myList) {
            tempMap.put(x.getKey(), x.getValue());
        }
        return tempMap;
    }

    public static void main(String[] args) {
        Board gameBoard = new Board(5); // GameBoard, 5 noduri
        Player[] players = new Player[4]; // Players, 4 playeri
        players[0] = new Player("Adrian", gameBoard, false, false); // Player Manual
        players[1] = new Player("Ben", gameBoard, false, false); // Player Manual
        players[2] = new Player("Corneliu", gameBoard, true, true); // Bot Smart
        players[3] = new Player("Damian", gameBoard, true, false); // Bot Simplu
        Thread[] threads = new Thread[players.length]; // Threads
        //Set TimeKeeper
        TimeKeeper myStopwatch = new TimeKeeper(gameBoard, 90000); // Round duration : 90 seconds!
        Thread timeKeeper = new Thread(myStopwatch);
        timeKeeper.setDaemon(true);

        //Establish Manual Players and Smart Bots
        Map<Thread, String> manualPlayers = new HashMap<>(); // Map with manual players
        Map<Thread, String> smartBots = new HashMap<>();
        int threadPlayerIndex = 0;
        for (Player x : players) {
            threads[threadPlayerIndex] = new Thread(x);
            if (!x.isBot())
                manualPlayers.put(threads[threadPlayerIndex], x.getName());
            else {
                if (x.isSmart()) {
                    smartBots.put(threads[threadPlayerIndex], x.getName());
                }
            }
            threadPlayerIndex++;
        }
        gameBoard.setCurrentThreadName(threads[0].getName());
        gameBoard.setManualPlayers(manualPlayers);
        gameBoard.setSmartBots(smartBots);

        // Graphics
        MainFrame appGraphics = new MainFrame();
        Node[] nodes = new Node[gameBoard.getIndex()];
        int index = 0;
        double alpha = 2 * Math.PI / nodes.length;
        for (int i = 0; i < nodes.length; i++) {
            nodes[index] = new Node();
            nodes[index].setName("Node " + index);
            double currX = 300 + 300 * Math.cos(alpha * i);
            double currY = 350 + 300 * Math.sin(alpha * i);
            nodes[index].setPosition(new Node.Position((int) currX, (int) currY));
            appGraphics.getCanvas().addNode(nodes[index]);
            index++;
        }
        for (Player x : players) {
            x.setNodes(nodes);
            x.setGraphInterface(appGraphics);
        }
        gameBoard.setGraphInterface(appGraphics);
        appGraphics.getCanvas().setSize(800, 800);
        appGraphics.getCanvas().setVisible(true);
        appGraphics.setSize(800, 800);
        appGraphics.setVisible(true);

        //Start Threads
        List<String> tempThreadNames = new ArrayList<>();
        for (Thread x : threads) {
            tempThreadNames.add(x.getName());
        }
        System.out.println("\n ----- Game Started! ----- \n");
        timeKeeper.start();
        gameBoard.setThreadNames(tempThreadNames);
        for (Thread x : threads) {
            x.start();
        }
        for (Thread x : threads) { // Wait for them to finish
            try {
                x.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("\n ----- The Round ended in " + ((endTime - myStopwatch.getStartTime()) / 1000) % 60 + " second(s), more accurately " + (endTime - myStopwatch.getStartTime()) + " millisecond(s)! ----- \n");
        System.out.println(" ----- The Threads ended execution! Printing Leaderboard! ----- \n");
        appGraphics.getLabel().setText("Round finished in: " + ((endTime - myStopwatch.getStartTime()) / 1000) % 60 + " second(s)! Check Console for Leaderboard!...");
        Map<Player, Integer> leaderboard = new HashMap<>();
        for (Player x : players) {
            leaderboard.put(x, x.getFinalScore());
        }
        Map<Player, Integer> finalLeaderboard = sortLeaderboard(leaderboard);
        int place = 1;
        for (Map.Entry<Player, Integer> x : finalLeaderboard.entrySet()) {
            System.out.println("Place #" + place + ": Player " + x.getKey().getName() + ", with a Score of " + x.getValue() + " points!");
            place++;
        }
        System.out.println("\n ----- Game Ended! ----- \n");
    }
}
