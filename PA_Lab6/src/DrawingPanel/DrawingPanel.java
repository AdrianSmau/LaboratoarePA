package DrawingPanel;

import DrawingPanel.Shapes.RegularPolygon;
import MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DrawingPanel extends JPanel {
    final static int W = 800, H = 600;
    final MainFrame frame;
    BufferedImage image;
    Graphics2D graphics;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public int getW() {
        return W;
    }

    public int getH() {
        return H;
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        drawShape(e.getX(), e.getY());
                        repaint();
                    }
                }
        );
    }

    private void drawShape(int x, int y) {
        int radius = ThreadLocalRandom.current().nextInt(20, 45 + 1);
        int sides = frame.getConfigPanel().getSidesField();
        String colorName = frame.getConfigPanel().getCurrentColor();

        //We generate a random color by randomly choosing the r,g and b values from 0 to 1
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color color = new Color(r, g, b);
        if (colorName.equals("BLACK"))
            color = Color.BLACK;
        graphics.setColor(color);
        graphics.fill(new RegularPolygon(x, y, radius, sides));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
