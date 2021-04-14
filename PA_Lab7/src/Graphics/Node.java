package Graphics;

import java.awt.*;

public class Node {
    private final Color color = Color.BLACK;
    private String name;
    private Position position;

    public Node() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public static class Position {
        int x;
        int y;

        public Position() {
            this(0, 0);
        }

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
