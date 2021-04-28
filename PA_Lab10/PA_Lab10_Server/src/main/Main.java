package main;

import SocketManager.SocketManager;

public class Main {
    public static void main(String[] args) {
        SocketManager socketManager = new SocketManager();
        socketManager.run();
    }
}
