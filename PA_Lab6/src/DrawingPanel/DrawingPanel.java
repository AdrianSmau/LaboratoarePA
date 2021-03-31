package DrawingPanel;

import DataStoring.DataStore;
import DrawingPanel.Shapes.RegularPolygon;
import MainFrame.MainFrame;
import main.myAntlr.returnTypes.DrawCmdReturn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

public class DrawingPanel extends JPanel {
    final static int W = 800, H = 600;
    final MainFrame frame;
    final private List<Point> pointList;
    final private JLabel Mode;
    final private DataStore myData;
    int oldX = 0, oldY = 0;
    boolean deleteMode;
    boolean freeHandMode;
    BufferedImage image;
    Graphics2D graphics;
    private Point last = new Point(0, 0);

    public DrawingPanel(MainFrame frame) {
        this.pointList = new ArrayList<>();
        this.Mode = new JLabel("");
        add(this.Mode);
        this.deleteMode = false;
        this.freeHandMode = false;
        this.frame = frame;
        this.myData = new DataStore();
        createOffscreenImage();
        init();
    }

    public static boolean areAllUnique(List<Point> myList) {
        Set<Point> mySet = new HashSet<>(myList);
        return mySet.size()==myList.size();
    }

    public static boolean isCircle(List<Point> myList) {
        if (!areAllUnique(myList.subList(3, myList.size() - 4))) {
            System.out.println("Not all points (that can't close a circle) are unique, so this is not a circle!...");
            return false;
        }
        for (Point x : myList.subList(0, 3)) {
            for (Point y : myList.subList(myList.size() - 3, myList.size() - 1)) {
                if (abs(x.getX() - y.getX()) < 3 && abs(x.getY() - y.getY()) < 3) {
                    System.out.println("Circle found at point: " + x + "!...");
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isLine(List<Point> myList) {
        double px0 = myList.get(0).getX();
        double py0 = myList.get(0).getY();
        double px1 = myList.get(1).getX();
        double py1 = myList.get(1).getY();

        double dx = px1 - px0;
        double dy = py1 - py0;

        for (int i = 0; i < myList.size(); i++) {
            double x = myList.get(i).getX();
            double y = myList.get(i).getY();
            if (dx * (y - py1) != dy * (x - px1)) {
                System.out.println("The first " + i + " points form a line!...");
                return false;
            }
        }
        return true;
    }

    public void activateDeleteMode() {
        this.Mode.setText("Delete Mode Enabled!...");
    }

    public void activateFreeHandMode() {
        this.Mode.setText("FreeHand Drawing Mode Enabled!...");
    }

    public void deactivateMode() {
        this.Mode.setText("");
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

    public boolean isDeleteMode() {
        return deleteMode;
    }

    public void setDeleteMode(boolean deleteMode) {
        this.deleteMode = deleteMode;
    }

    public boolean isFreeHandMode() {
        return freeHandMode;
    }

    public void setFreeHandMode(boolean freeHandMode) {
        this.freeHandMode = freeHandMode;
    }

    public int getW() {
        return W;
    }

    public int getH() {
        return H;
    }

    public DataStore getMyData() {
        return myData;
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
        graphics.setPaint(Color.BLACK);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (freeHandMode && e.getX() < W && e.getY() < H) {
                            oldX = e.getX();
                            oldY = e.getY();
                        } else {
                            if (deleteMode) {
                                if (e.getX() < W && e.getY() < H) {
                                    removeShape(e.getX(), e.getY());
                                    repaint();
                                }
                            } else {
                                if (e.getX() < W && e.getY() < H) {
                                    drawShape(e.getX(), e.getY());
                                    repaint();
                                }
                            }
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (isFreeHandMode()) {
                            if (!isLine(pointList))
                                System.out.println("The drawn shape is NOT a line!...\n");
                            else
                                System.out.println("The drawn shape is a line!...\n");
                            if (!isCircle(pointList))
                                System.out.println("There aren't any Circles in the FreeHand drawing!...\n");
                            else
                                System.out.println("There is at least one Circle in the FreeHand drawing!...\n");
                            pointList.clear();
                        }
                    }
                }
        );
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (freeHandMode) {
                    if (graphics != null && !last.equals(e.getPoint())) {
                        graphics.drawLine(oldX, oldY, e.getX(), e.getY());
                        pointList.add(e.getPoint());
                        oldX = e.getX();
                        oldY = e.getY();
                        last = e.getPoint();
                        repaint();
                    }
                }
            }
        });

    }

    public void drawByCommand(DrawCmdReturn myObj) {
        if (myObj.getRetCoords().get(0) > 0 && myObj.getRetCoords().get(0) < 600 && myObj.getRetCoords().get(1) > 0 && myObj.getRetCoords().get(1) < 800 && myObj.getRetRadius() > 20 && myObj.getRetRadius() < 45) {
            Integer coordX = myObj.getRetCoords().get(0);
            Integer coordY = myObj.getRetCoords().get(1);
            Integer radius = myObj.getRetRadius();
            Color color = myObj.getRetColor();
            System.out.println("Command executed! Drawing a " + myObj.getRetShape() + ", colored " + color.toString() + ",at: (" + coordX + "," + coordY + "), with the radius " + radius + "!...\n");
            int sides = frame.getConfigPanel().getSidesField();
            switch (myObj.getRetShape()) {
                case "polygon":
                    Shape tempShape = new RegularPolygon(coordX, coordY, radius, sides);
                    this.myData.addToDataStore(tempShape, color);
                    break;
                case "circle":
                    Shape tempShape1 = new Ellipse2D.Double(coordX, coordY, radius, radius);
                    this.myData.addToDataStore(tempShape1, color);
                    break;
                case "square":
                    Shape tempShape3 = new Rectangle(coordX, coordY, radius, radius);
                    this.myData.addToDataStore(tempShape3, color);
                    break;
                case "rectangle":
                    int radius2 = ThreadLocalRandom.current().nextInt(20, 45 + 1);
                    Shape tempShape4 = new Rectangle(coordX, coordY, radius, radius2);
                    this.myData.addToDataStore(tempShape4, color);
                    break;
            }
        } else
            System.out.println("DRAW --- Even though the command is syntactically correct, the input values are not valid!...\n");
    }

    public void deleteByCommand(int x, int y) {
        if (x > 0 && x < 600 && y > 0 && y < 800) {
            System.out.println("Command executed! Deleting the shape at: (" + x + "," + y + ")!...\n");
            removeShape(x, y);
        } else
            System.out.println("DELETE --- Even though the command is syntactically correct, the input values are not valid!...\n");
    }

    private void refreshGraphics() {
        Graphics2D imageGraphics = image.createGraphics();
        imageGraphics.setColor(Color.WHITE);
        imageGraphics.fillRect(0, 0, frame.getCanvas().getW(), frame.getCanvas().getH());
        this.graphics = imageGraphics;
        this.repaint();
    }

    private void removeShape(int x, int y) {
        for (int i = this.myData.getShapes().size() - 1; i >= 0; i--) {
            if (this.myData.getShapes().get(i).contains(x, y)) {
                this.myData.removeFromDataStore(this.myData.getShapes().get(i));
                refreshGraphics();
                break;
            }
        }
    }

    private void drawShape(int x, int y) {
        int radius = ThreadLocalRandom.current().nextInt(20, 45 + 1);
        int sides = frame.getConfigPanel().getSidesField();
        String colorName = frame.getConfigPanel().getCurrentColor();
        String shapeName = frame.getConfigPanel().getCurrentShape();

        //We generate a random color by randomly choosing the r,g and b values from 0 to 1
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color color = new Color(r, g, b);
        if (colorName.equals("BLACK"))
            color = Color.BLACK;

        switch (shapeName) {
            case "REGULAR POLYGON":
                Shape tempShape = new RegularPolygon(x, y, radius, sides);
                this.myData.addToDataStore(tempShape, color);
                break;
            case "CIRCLE":
                Shape tempShape2 = new Ellipse2D.Double(x, y, radius, radius);
                this.myData.addToDataStore(tempShape2, color);
                break;
            case "SQUARE":
                Shape tempShape3 = new Rectangle(x, y, radius, radius);
                this.myData.addToDataStore(tempShape3, color);
                break;
            case "RECTANGLE":
                int radius2 = ThreadLocalRandom.current().nextInt(20, 45 + 1);
                Shape tempShape4 = new Rectangle(x, y, radius, radius2);
                this.myData.addToDataStore(tempShape4, color);
                break;
        }
    }

    @Override
    public void update(Graphics g) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
        this.graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Shape shape : this.myData.getShapes()) {
            Color tempColor = this.myData.getShapeColorMap().get(shape);
            this.graphics.setColor(tempColor);
            this.graphics.fill(shape);
        }
        repaint();
    }
}
