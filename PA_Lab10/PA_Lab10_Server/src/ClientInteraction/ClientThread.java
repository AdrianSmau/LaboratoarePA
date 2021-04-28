package ClientInteraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println("\n ----- Current Client Thread: '" + Thread.currentThread().getName() + "'!... ----- ");
        try {
            int iteration = 0;
            while (true) {
                System.out.println("\n [Iteration #" + iteration + "] Awaiting Input!...");
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String response = "Server received the request: '" + request + "'!";
                System.out.println("Parsing command!...");
                request = request.toLowerCase();
                String[] parsedCmd = request.split(" ");
                boolean stopped = false;
                if (parsedCmd[0].equals("stop")) {
                    response = "Server stopped!...";
                    System.out.println("Client has stopped execution!...");
                    stopped = true;
                } else {
                    System.out.print("Output: ");
                    System.out.print("[ ");
                    for (String x : parsedCmd) {
                        System.out.print(x + " ");
                    }
                    System.out.print("]\n");
                }
                out.println(response);
                out.flush();
                iteration++;
                if (stopped)
                    break;
            }
        } catch (IOException e) {
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
