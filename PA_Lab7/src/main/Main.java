package main;

import Board.Board;
import Players.Player;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Board gameBoard = new Board(4);
        Player[] players = new Player[3];
        players[0] = new Player("Adrian", gameBoard);
        players[1] = new Player("Ben", gameBoard);
        players[2] = new Player("Corneliu", gameBoard);
        Thread[] threads = new Thread[players.length];
        int threadPlayerIndex = 0;
        for (Player x : players) {
            threads[threadPlayerIndex] = new Thread(x);
            threadPlayerIndex++;
        }
        gameBoard.setCurrentThreadName(threads[0].getName());
        List<String> tempThreadNames = new ArrayList<>();
        for(Thread x : threads){
            tempThreadNames.add(x.getName());
        }
        gameBoard.setThreadNames(tempThreadNames);
        for (Thread x : threads) {
            x.start();
        }
    }
}
