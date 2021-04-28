package SocketManager;

import ClientInteraction.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketManager {
    private static final int PORT = 8100;

    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                if (Thread.activeCount() > 2)
                    System.out.println(" [BACKGROUND] Waiting for a Client!...");
                else
                    System.out.println(" Waiting for a Client!...");
                Socket socket = serverSocket.accept();
                System.out.println("Client accepted!...");
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            System.err.println(" [ERROR] IOException caught!: " + e.getMessage());
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                System.err.println(" [ERROR] IOException caught when trying to close ServerSocket!...");
            }
        }
    }
}
