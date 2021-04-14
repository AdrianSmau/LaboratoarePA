package Board;

import Graphics.MainFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final ReentrantLock lock = new ReentrantLock(true);
    final private int index;
    final private List<Token> tokens;
    private Map<Thread, String> manualPlayers;
    private Map<Thread, String> smartBots;
    private List<String> threadNames;
    private String currentThreadName;
    private int tokensAvailable;
    private MainFrame graphInterface;

    public Board(int index) {
        this.index = index;
        this.currentThreadName = null;
        this.threadNames = new ArrayList<>();
        this.tokensAvailable = index * (index - 1);
        tokens = new ArrayList<>();
        for (int i = 0; i <= index - 1; i++) {
            for (int j = 0; j <= index - 1; j++) {
                if (i != j) {
                    tokens.add(new Token(i, j, (i * 10 + j)));
                }
            }
        }
    }

    public void setGraphInterface(MainFrame graphInterface) {
        this.graphInterface = graphInterface;
    }

    public int getIndex() {
        return index;
    }

    public void setManualPlayers(Map<Thread, String> manualPlayers) {
        this.manualPlayers = manualPlayers;
    }

    public void setSmartBots(Map<Thread, String> smartBots) {
        this.smartBots = smartBots;
    }

    public void setCurrentThreadName(String currentThreadName) {
        this.currentThreadName = currentThreadName;
    }

    public void setThreadNames(List<String> threadNames) {
        this.threadNames = threadNames;
    }

    public int getTokensAvailable() {
        return tokensAvailable;
    }

    public void setTokensAvailable(int tokensAvailable) {
        this.tokensAvailable = tokensAvailable;
    }

    public synchronized Token getExtraction() {
        boolean skipped = false;
        lock.lock();
        try {
            this.graphInterface.getLabel().setText("Current Thread: " + this.currentThreadName + "!...");
            if (!Thread.currentThread().getName().equals(this.currentThreadName)) {
                skipped = true;
                lock.unlock();
                return null;
            } else {
                if (this.threadNames.indexOf(this.currentThreadName) == this.threadNames.size() - 1)
                    this.currentThreadName = this.threadNames.get(0);
                else
                    this.currentThreadName = this.threadNames.get(this.threadNames.indexOf(this.currentThreadName) + 1);
            }
            if (this.manualPlayers.containsKey(Thread.currentThread())) {
                if (tokensAvailable > 0) {
                    String commandLine = null;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("\nIt is " + this.manualPlayers.get(Thread.currentThread()) + "'s turn! Please select the wanted token!...");
                    System.out.println("Please write below the two coordinates of the wanted token - for instance 0 1, or 1 2, etc...");
                    System.out.println(tokensAvailable);
                    while (true) {
                        System.out.println("The available tokens are: ");
                        for (Token x : this.tokens) {
                            if (x.isAvailable()) {
                                System.out.print("(" + x.getNumber1() + "," + x.getNumber2() + ") ");
                            }
                        }
                        System.out.print("\ncmd>");
                        try {
                            commandLine = reader.readLine();
                        } catch (IOException ok) {
                            System.err.println("IOException caught when trying to get the user choice!...");
                        }
                        String[] eachCommand = {};
                        if (commandLine != null)
                            eachCommand = commandLine.split(" ");
                        if (eachCommand.length == 2 && Integer.parseInt(eachCommand[0]) >= 0 && Integer.parseInt(eachCommand[0]) < index && Integer.parseInt(eachCommand[1]) >= 0 && Integer.parseInt(eachCommand[1]) < index) {
                            for (Token x : this.tokens) {
                                if (x.getNumber1() == Integer.parseInt(eachCommand[0]) && x.getNumber2() == Integer.parseInt(eachCommand[1]) && x.isAvailable()) {
                                    x.setAvailable(false);
                                    this.tokensAvailable--;
                                    return x;
                                }
                            }
                            System.out.println("The token you tried to access is not available! Please try again!...");
                        }
                    }
                }
            }
            if (this.smartBots.containsKey(Thread.currentThread())) {
                if (tokensAvailable > 0) {
                    for (int i = tokens.size() - 1; i >= 0; i--) {
                        if (tokens.get(i).isAvailable()) {
                            System.out.println("\n[BOT] I made my move!...");
                            tokens.get(i).setAvailable(false);
                            this.tokensAvailable--;
                            return tokens.get(i);
                        }
                    }
                }
            } else {
                if (tokensAvailable > 0) {
                    for (Token x : this.tokens) {
                        if (x.isAvailable()) {
                            System.out.println("\n[BOT] I made my move!...");
                            x.setAvailable(false);
                            this.tokensAvailable--;
                            return x;
                        }
                    }
                }
            }
            return null;
        } finally {
            if (!skipped)
                lock.unlock();
        }
    }
}
