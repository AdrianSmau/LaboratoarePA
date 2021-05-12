package SocketManager;

import ClientInteraction.ClientThread;
import User.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocketManager {
    private static final int PORT = 8100;
    private final List<ClientThread> activeThreads = new ArrayList<>();
    private final Connection conn;
    private final List<User> users = new ArrayList<>();
    private boolean ended = false;

    public SocketManager(Connection conn) {
        this.conn = conn;
    }

    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                if (this.ended) {
                    System.out.println("\n ----- Ending Server! Terminating Threads already in progress and shutting down the Server!... ----- \n");
                    System.out.println("Waiting for all Threads to finish!...");
                    break;
                }
                if (Thread.activeCount() > 2)
                    System.out.println(" [BACKGROUND] Waiting for a Client!...");
                else
                    System.out.println(" Waiting for a Client!...");
                Socket socket = serverSocket.accept();
                System.out.println("Client accepted!...");
                ClientThread currentClient = new ClientThread(socket, this.conn, users);
                this.activeThreads.add(currentClient);
                currentClient.start();
                try {
                    currentClient.join();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                User prevUser = currentClient.getCurrUser();

                // --- Friends Update

                if (prevUser != null) {
                    boolean exists = false;
                    for (User x : this.users) {
                        if (x.getName().equals(prevUser.getName())) {
                            exists = true;
                            for (String y : prevUser.getFriends()) {
                                if (!x.getFriends().contains(y)) {
                                    x.addFriend(y);
                                    for (User z : this.users) {
                                        if (z.getName().equals(y)) {
                                            z.addFriend(x.getName());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!exists) {
                        this.users.add(prevUser);
                        for (String x : prevUser.getFriends()) {
                            for (User y : this.users) {
                                if (y.getName().equals(x)) {
                                    y.addFriend(prevUser.getName());
                                }
                            }
                        }
                    }

                    // --- End of Updating Friends

                    // --- Sending Messages

                    exists = false;
                    for (User x : this.users) {
                        if (x.getName().equals(prevUser.getName())) {
                            exists = true;
                            for (Map.Entry<String, String> entry : x.getSendMessages().entrySet()) {
                                for (User y : this.users) {
                                    if (y.getName().equals(entry.getKey())) {
                                        y.addMessage(x.getName(), entry.getValue());
                                    }
                                }
                            }
                            x.setSendMessages(new HashMap<>());
                        }
                    }
                    if (!exists) {
                        this.users.add(prevUser);
                        for (Map.Entry<String, String> entry : prevUser.getSendMessages().entrySet()) {
                            for (User y : this.users) {
                                if (y.getName().equals(entry.getKey())) {
                                    y.addMessage(y.getName(), entry.getValue());
                                }
                            }
                        }
                        prevUser.setSendMessages(new HashMap<>());
                    }

                    // --- End of Sending Messages
                }

                System.out.println("\n ----- Current status of the DataBase: ----- \n");
                for (User x : this.users) {
                    System.out.println("User " + x.getName() + " has the friends: " + x.getFriends());
                    System.out.println("User also has the following unread messages:");
                    for (Map.Entry<String, String> entry : x.getMessages().entrySet()) {
                        System.out.println("\tMessage '" + entry.getValue() + "' from User " + entry.getKey());
                    }
                    System.out.println();
                }
                this.activeThreads.remove(currentClient);
                this.ended = currentClient.isEnded();
            }
        } catch (IOException e) {
            System.err.println(" [ERROR] IOException caught!: " + e.getMessage());
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
                for (ClientThread x : this.activeThreads) {
                    try {
                        x.join();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (IOException e) {
                System.err.println(" [ERROR] IOException caught when trying to close ServerSocket!...");
            }
        }
    }
}
