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
            Socket socket = new Socket(serverAddress, PORT);

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
                }
                out.println(commandLine);
                String response = in.readLine();
                System.out.println(response);
            }
            socket.close();
        } catch (IOException ex) {
            System.err.println(" [ERROR] IOException caught in Client!...");
        } finally {
            System.out.println("Client has finished execution!...");
        }
    }
}
