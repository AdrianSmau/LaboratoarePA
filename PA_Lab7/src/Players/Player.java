package Players;

import Board.Board;
import Board.Token;
import Graphics.Edge;
import Graphics.MainFrame;
import Graphics.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements Runnable {
    private final String name;
    private final List<Token> myTokens;
    private final Board gameBoard;
    private final boolean isBot;
    private final boolean isSmart;
    final private List<String> matchHistory = new ArrayList<>();
    private int finalScore;
    private Node[] nodes;
    private MainFrame graphInterface;

    public Player(String name, Board gameBoard, boolean isBot, boolean isSmart) {
        this.finalScore = 0;
        this.isBot = isBot;
        this.isSmart = isSmart;
        this.gameBoard = gameBoard;
        this.myTokens = new ArrayList<>();
        this.name = name;
    }

    public void setGraphInterface(MainFrame graphInterface) {
        this.graphInterface = graphInterface;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public boolean isBot() {
        return isBot;
    }

    public String getName() {
        return name;
    }

    private int calculateScore() {
        int score = 0;
        for (Token x : this.myTokens) {
            score += x.getValue();
        }
        return score;
    }

    public int calculateMaxCycle() {
        Map<Integer, List<Integer>> graphMap = new HashMap<>();
        Map<Integer, Integer> visited = new HashMap<>();
        for (Token x : this.myTokens) {
            if (!graphMap.containsKey(x.getNumber1())) {
                graphMap.put(x.getNumber1(), new ArrayList<>());
            }
            graphMap.get(x.getNumber1()).add(x.getNumber2());
            visited.put(x.getNumber1(), 0);
            visited.put(x.getNumber2(), 0);
        }
        int[] count = new int[1];
        int[] currCycleLen = new int[1];
        int max = 0;
        for (Integer x : visited.keySet()) {
            dfs(x, x, count, currCycleLen, visited, graphMap);
            if (currCycleLen[0] > max) {
                max = currCycleLen[0];
            }
            currCycleLen[0] = 0;
            visited.put(x, 1);
        }
        return max;
    }

    private void dfs(int start, int v, int[] count, int[] currCycleLen, Map<Integer, Integer> visited, Map<Integer, List<Integer>> graphMap) {
        if (visited.get(v) == 1) {
            currCycleLen[0]++;
            if (start == v) {
                count[0]++;
            }
            return;
        }
        visited.put(v, 1);
        currCycleLen[0] = 1;
        if (graphMap.containsKey(v)) {
            for (Integer x : graphMap.get(v)) {
                dfs(start, x, count, currCycleLen, visited, graphMap);
            }
        }
        visited.put(v, 0);
    }

    public boolean isSmart() {
        return isSmart;
    }

    public List<String> getMatchHistory() {
        return matchHistory;
    }

    @Override
    public void run() {
        while (this.gameBoard.getTokensAvailable() > 0) {
            Token tempToken = this.gameBoard.getExtraction();
            if (tempToken != null) {
                this.myTokens.add(tempToken);
                matchHistory.add("Player " + this.name + " has extracted the token: " + tempToken);
                Node tempNode1 = this.nodes[tempToken.getNumber1()];
                Node tempNode2 = this.nodes[tempToken.getNumber2()];
                Edge currEdge = new Edge(tempNode1, tempNode2);
                currEdge.setPlayerName(this);
                currEdge.setValue(tempToken.getValue());
                this.graphInterface.getCanvas().addEdge(currEdge);
                this.graphInterface.getCanvas().repaint();
            }
        }
        System.out.println("\n[NOTIFICATION] Player " + this.getName() + " has finished execution and will now print its match history!...\n");
        for (String x : this.getMatchHistory()) {
            System.out.println(x);
        }
        int tokenScore = calculateScore();
        int maxCycle = calculateMaxCycle();
        if (maxCycle > 1) {
            this.finalScore = tokenScore + this.gameBoard.getIndex() * this.gameBoard.getIndex() * maxCycle;
            if (maxCycle == this.myTokens.size()) {
                this.finalScore = this.finalScore * maxCycle;
            }
        } else
            this.finalScore = tokenScore;
    }
}
