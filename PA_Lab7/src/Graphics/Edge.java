package Graphics;

import Players.Player;

import java.awt.*;

public class Edge {
    private final Color color = Color.BLACK;
    private final Node.Position startPosition;
    private final Node.Position endPosition;
    private final String name1;
    private final String name2;
    private int value;
    private String playerName;

    public Edge(Node node1, Node node2) {
        startPosition = node1.getPosition();
        endPosition = node2.getPosition();
        name1 = node1.getName();
        name2 = node2.getName();
    }

    public Node.Position getStartPosition() {
        return startPosition;
    }

    public Node.Position getEndPosition() {
        return endPosition;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public Node.Position getCenterPosition() {
        Node.Position pos = new Node.Position();
        pos.x = (startPosition.x + endPosition.x) / 2;
        pos.y = (startPosition.y + endPosition.y) / 2;
        return pos;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int tokenVal) {
        this.value = tokenVal;
    }

    public Color getColor() {
        return color;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(Player player) {
        this.playerName = player.getName();
    }

    public float getSlope() {
        float deltaX = startPosition.x - endPosition.x;
        float deltaY = startPosition.y - endPosition.y;
        return deltaY / deltaX;
    }
}
