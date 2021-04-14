package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class GamePanel extends JComponent {
    private static final int MINIMUM_EDGE_SIZE = 32;
    private static final int EDGE_PADDING = 8;
    private final Color bgColor = Color.WHITE;

    private final List<Edge> edges = new CopyOnWriteArrayList<>();
    private final Map<Edge, Integer> overlapping = new HashMap<>();
    private final List<Node> nodes = new ArrayList<>();

    private FontMetrics metrics;

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
        overlapping.put(edge, 0);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics = (Graphics2D) g.create();
        metrics = graphics.getFontMetrics();
        graphics.setColor(bgColor);
        paintBg(graphics);
        drawEdges(graphics);
        drawNodes(graphics);
    }

    private void drawNodes(Graphics2D gfx) {
        for (Node x : nodes) {
            drawNode(gfx, x);
        }
    }

    private void drawNode(Graphics2D gfx, Node node) {
        gfx.setColor(bgColor);
        int nameSize = measure(node.getName());
        gfx.fillOval(update(node.getPosition().x), update(node.getPosition().y), nameSize, nameSize);
        gfx.setColor(node.getColor());
        Rectangle2D rect = metrics.getStringBounds(node.getName(), gfx);
        int strWidth = rect.getBounds().width;
        int strHeight = rect.getBounds().height;
        int x = node.getPosition().x;
        int y = node.getPosition().y;
        gfx.drawString(node.getName(), update(x + (nameSize - strWidth) / 2), update(y + (nameSize + strHeight / 2) / 2));
        gfx.drawOval(update(node.getPosition().x), update(node.getPosition().y), nameSize, nameSize);
    }

    private int measure(String txt) {
        int width = metrics.stringWidth(txt);
        if (width < MINIMUM_EDGE_SIZE - 5) {
            return MINIMUM_EDGE_SIZE;
        }
        return width + 2 * EDGE_PADDING;
    }

    private int measureWOPadding(String txt) {
        return metrics.stringWidth(txt);
    }

    private void drawEdges(Graphics2D gfx) {
        for (Edge x : edges) {
            drawEdge(gfx, x);
        }
    }

    private void drawEdge(Graphics2D gfx, Edge edge) {
        gfx.setColor(edge.getColor());
        Node.Position start = edge.getStartPosition();
        Node.Position end = edge.getEndPosition();
        int sWidth = measure(edge.getName1());
        int eWidth = measure(edge.getName2());
        gfx.drawLine(update(start.x + sWidth / 2), update(start.y + sWidth / 2), update(end.x + eWidth / 2), update(end.y + eWidth / 2));
        gfx.setStroke(new BasicStroke(1));

        int overlapPrevention = 0;
        for (Edge x : this.edges) {
            if (x.getStartPosition() == edge.getEndPosition() && x.getEndPosition() == edge.getStartPosition()) {
                if (overlapping.get(x) == 0) {
                    overlapPrevention = 17;
                    overlapping.put(edge, 1);
                }
            }
        }

        Node.Position centerPos = edge.getCenterPosition();
        int width = measureWOPadding(edge.getPlayerName() + "," + edge.getValue() + "");
        float slope = edge.getSlope();
        if (slope < 0)
            gfx.drawString(edge.getPlayerName() + "," + edge.getValue() + "", update(centerPos.x - width / 2), update(centerPos.y - width / 2) + overlapPrevention);
        else
            gfx.drawString(edge.getPlayerName() + "," + edge.getValue() + "", update(centerPos.x + width / 2), update(centerPos.y - width / 2) + overlapPrevention);
    }

    public int update(int i) {
        return i + 20;
    }

    private void paintBg(Graphics2D gfx) {
        gfx.fillRect(0, 0, getWidth(), getHeight());
    }
}
