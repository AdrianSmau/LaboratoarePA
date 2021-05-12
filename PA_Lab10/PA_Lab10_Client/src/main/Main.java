package main;

import ServerInteraction.Client;

public class Main {

    public static void main(String[] args) {
        Client[] clients = new Client[4];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = new Client();
        }
        int index = 0;
        for (Client x : clients) {
            System.out.println("\n ----- Client " + index + "!... ----- \n");
            x.run();
            index++;
        }
    }
}
