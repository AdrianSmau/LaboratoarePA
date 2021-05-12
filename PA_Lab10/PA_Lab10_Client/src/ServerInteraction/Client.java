package ServerInteraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String serverAddress = "127.0.0.1";
    private static final int PORT = 8100;

    public void run() {
        try {
            System.out.print("Loading the Server");
            try {
                Thread.sleep(200);
                for (int i = 0; i < 3; i++) {
                    System.out.print(".");
                    Thread.sleep(330);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                System.out.println();
            }
            System.out.println("Checking if the Server is available!...");
            Socket socket = new Socket(serverAddress, PORT);
            System.out.println("SUCCESS!...");
            System.out.println("Welcome!\nAvailable Commands:\n - register *name* - register User in the DataBase\n - login *name* - log in the DataBase\n - [REQUIRES LOGIN] friend *name1* *name2* ... - add friends\n - [REQUIRES LOGIN] send *message* - sends message to all the users\n - [REQUIRES LOGIN] read - reads all the received messages\n - [REQUIRES LOGIN] end - ends the Server\n - stop - ends the current Connection to the Server");
            System.out.println("[NOTIFICATION] Server timeout is set to 30 seconds! If you are inactive for 30 seconds, you will be timed out!...");
            System.out.println("[NOTIFICATION] Usernames and commands are 'case insensitive'!...");
            System.out.println("[NOTIFICATION] All the actions and changes made will be applied after you Log Out!...");
            System.out.println("[NOTIFICATION] You can only send one message per session!...");

            String commandLine = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("cmd> ");
                try {
                    commandLine = reader.readLine();
                } catch (IOException ex) {
                    System.err.println(" [ERROR] IOException caught when trying to read from Command Line in Client!...");
                }
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String commandLineCopy = commandLine;

                if (commandLineCopy != null) {
                    commandLineCopy = commandLineCopy.toLowerCase();
                    commandLineCopy = commandLineCopy.replace(" ", "");
                    if (commandLineCopy.equals("stop")) {
                        out.println("stop");
                        break;
                    }
                    if (commandLineCopy.equals("end")) {
                        commandLine = "end";
                    }
                }
                out.println(commandLine);
                String response = in.readLine();
                System.out.println(response);
            }
            socket.close();
        } catch (IOException ex) {
            System.err.println(" [ERROR] IOException caught in Client!: " + ex.getMessage());
        } finally {
            System.out.println("Client has finished execution!...");
        }
    }
}
