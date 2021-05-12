package ClientInteraction;

import User.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ClientThread extends Thread {
    private final Socket socket;
    private final Connection conn;
    private final List<User> users;
    private boolean ended = false;
    private boolean logged = false;
    private User currUser = null;
    private boolean msgSent = false;

    public ClientThread(Socket socket, Connection conn, List<User> users) {
        this.users = users;
        this.conn = conn;
        this.socket = socket;
        try {
            this.socket.setSoTimeout(30000);
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
    }

    public User getCurrUser() {
        return currUser;
    }

    public boolean isEnded() {
        return ended;
    }

    public void run() {
        System.out.println("\n ----- Current Client Thread: '" + Thread.currentThread().getName() + "'!... ----- ");
        try {
            int iteration = 0;
            while (true) {
                System.out.println("\n[" + Thread.currentThread().getName() + "] [Iteration #" + iteration + "] Awaiting Input!...");
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String response = null;
                System.out.println("Parsing command!...");
                request = request.toLowerCase();
                String[] parsedCmd = request.split(" ");
                boolean stopped = false;
                switch (parsedCmd[0]) {
                    case "stop":
                        response = "Server stopped!...";
                        System.out.println("Client has stopped execution!...");
                        stopped = true;
                        break;
                    case "end":
                        if (!logged) {
                            response = "You do not have permission to end the Server! Log In or Register first in order to be able to do so!...";
                            System.out.println("Client not logged in!...");
                        } else {
                            response = "Server ended! No more connections will be accepted once this one has ended!...";
                            System.out.println("Client has stopped the Server! Action will take effect once the calling Thread ends!...");
                            this.ended = true;
                        }
                        break;
                    case "send":
                        if (!logged) {
                            response = "You do not have permission to Send Messages! Log In or Register first in order to be able to do so!...";
                            System.out.println("Client not logged in!...");
                        } else {
                            if (!this.msgSent) {
                                StringBuilder messageToBeSent = new StringBuilder();
                                for (int i = 1; i < parsedCmd.length; i++) {
                                    messageToBeSent.append(parsedCmd[i]);
                                    messageToBeSent.append(" ");
                                }
                                Set<String> currUserFriends = new HashSet<>();
                                currUserFriends.addAll(this.currUser.getFriends());
                                for (User x : this.users) {
                                    if (this.currUser.getName().equals(x.getName())) {
                                        currUserFriends.addAll(x.getFriends());
                                    }
                                }
                                if (currUserFriends.size() == 0) {
                                    response = "You do not have any Friends!...";
                                    System.out.println("No friends found!...");
                                } else {
                                    response = "Message '" + messageToBeSent + "' sent to " + currUserFriends.size() + " friend(s)! : " + currUserFriends + "...";
                                    System.out.println("Message sent!...");
                                    for (String x : currUserFriends) {
                                        this.currUser.sendMessage(x, messageToBeSent.toString());
                                    }
                                }
                                this.msgSent = true;
                            } else {
                                response = "You already sent one message this session! Log out in order for the changes to take place!...";
                            }
                        }
                        break;
                    case "read":
                        if (!logged) {
                            response = "You do not have permission to Read Messages! Log In or Register first in order to be able to do so!...";
                            System.out.println("Client not logged in!...");
                        } else {
                            List<String> receivedMessages = new ArrayList<>();
                            for (User x : this.users) {
                                if (currUser.getName().equals(x.getName())) {
                                    for (Map.Entry<String, String> entry : x.getMessages().entrySet()) {
                                        receivedMessages.add("Message: '" + entry.getValue() + "' - from User: " + entry.getKey());
                                    }
                                }
                            }
                            if (receivedMessages.size() == 0) {
                                response = "You do not have any Messages!...";
                                System.out.println("No messages found!...");
                            } else {
                                response = receivedMessages.size() + " message(s) found! : ";
                                response += receivedMessages.toString();
                                this.currUser.setMessages(new HashMap<>());
                                for (User x : this.users) {
                                    if (this.currUser.getName().equals(x.getName())) {
                                        x.setMessages(new HashMap<>());
                                    }
                                }
                            }
                        }
                        break;
                    case "friend":
                        if (!logged) {
                            response = "You do not have permission to Add Friends! Log In or Register first in order to be able to do so!...";
                            System.out.println("Client not logged in!...");
                        } else {
                            List<String> feedbacks = new ArrayList<>();
                            for (int i = 1; i < parsedCmd.length; i++) {
                                try {
                                    String query = "SELECT * FROM users WHERE name = ?";
                                    PreparedStatement ps = conn.prepareStatement(query);
                                    ps.setString(1, parsedCmd[i]);
                                    ResultSet rs = ps.executeQuery();
                                    boolean check = rs.next();
                                    if (check) {
                                        feedbacks.add("User " + parsedCmd[i] + " is now friends with " + this.currUser.getName() + " !...");
                                        if (!this.currUser.getFriends().contains(parsedCmd[i]))
                                            this.currUser.addFriend(parsedCmd[i]);
                                        System.out.println("Friends added!...");
                                    } else {
                                        feedbacks.add("User " + parsedCmd[i] + " does not exist in the DataBase!...");
                                        System.out.println("Adding Friends Failed!...");
                                    }
                                } catch (SQLException ex) {
                                    System.err.println("[ERROR] SQLException caught when trying to check existence of User in DataBase!...");
                                }
                            }
                            boolean isFirst = false;
                            for (String x : feedbacks) {
                                if (!isFirst) {
                                    isFirst = true;
                                    response = x;
                                } else {
                                    response += ", ";
                                    response += x;
                                }
                            }
                        }
                        break;
                    case "login":
                        if (logged) {
                            response = "You are already logged in with the account: " + this.currUser.getName() + " !...";
                        } else {
                            StringBuilder stringBuilderL = new StringBuilder(parsedCmd[1]);
                            for (int i = 2; i < parsedCmd.length; i++) {
                                stringBuilderL.append(" ");
                                stringBuilderL.append(parsedCmd[i]);
                            }
                            String userNameL = stringBuilderL.toString();
                            try {
                                String query = "SELECT * FROM users WHERE name = ?";
                                PreparedStatement ps = conn.prepareStatement(query);
                                ps.setString(1, userNameL);
                                ResultSet rs = ps.executeQuery();
                                boolean check = rs.next();
                                if (check) {
                                    response = "User " + userNameL + " is now logged in!...";
                                    this.logged = true;
                                    this.currUser = new User(userNameL);
                                    System.out.println("User logged in!...");
                                } else {
                                    response = "User " + userNameL + " does not exist in the DataBase! Register first!...";
                                    System.out.println("Log In Failed!...");
                                }
                            } catch (SQLException ex) {
                                System.err.println("[ERROR] SQLException caught when trying to check existence of User in DataBase!...");
                            }
                        }
                        break;
                    case "register":
                        if (logged) {
                            response = "You are already logged in with the account: " + this.currUser.getName() + " !...";
                        } else {
                            StringBuilder stringBuilderR = new StringBuilder(parsedCmd[1]);
                            for (int i = 2; i < parsedCmd.length; i++) {
                                stringBuilderR.append(" ");
                                stringBuilderR.append(parsedCmd[i]);
                            }
                            String userNameR = stringBuilderR.toString();
                            try {
                                String query = "SELECT * FROM users WHERE name = ?";
                                PreparedStatement ps = conn.prepareStatement(query);
                                ps.setString(1, userNameR);
                                ResultSet rs = ps.executeQuery();
                                boolean check = rs.next();
                                if (check) {
                                    response = "User " + userNameR + " is already present in the DataBase! Please try again!...";
                                    System.out.println("User NOT added to DataBase!...");
                                } else {
                                    this.logged = true;
                                    this.currUser = new User(userNameR);
                                    response = "User " + userNameR + " added to the DataBase!...";
                                    System.out.println("User added to DataBase!...");
                                    query = "INSERT INTO users VALUES (?)";
                                    ps = conn.prepareStatement(query);
                                    ps.setString(1, userNameR);
                                    ps.executeUpdate();
                                }
                            } catch (SQLException ex) {
                                System.err.println("[ERROR] SQLException caught when trying to check existence of User in DataBase!...");
                            }
                        }
                        break;
                    default:
                        response = "Command unrecognized! Please try again!...";
                        System.out.print("Output: ");
                        System.out.print("[ ");
                        for (String x : parsedCmd) {
                            System.out.print(x + " ");
                        }
                        System.out.print("]\n");
                        System.out.println("Command not recognized! Please try again!...");

                }
                out.println(response);
                out.flush();
                iteration++;
                if (stopped)
                    break;
            }
        } catch (IOException e) {
            if (e.getMessage().equals("Read timed out")) {
                System.out.println("[TimeKeeper] The idling limit has been reached! Client timed out!...");
            } else
                System.err.println(" [ERROR] IOException caught! Communication error!: " + e.getMessage());
        } finally {
            System.out.println(" ----- Client Thread '" + Thread.currentThread().getName() + "' Ended!... ----- ");
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(" [ERROR] IOException caught when trying to close Socket: " + this.socket);
            }
        }
    }
}
