package Graphics;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private GamePanel canvas;
    private JLabel label;

    public MainFrame() {
        super("Token Game");
        init();
    }

    public GamePanel getCanvas() {
        return canvas;
    }

    public JLabel getLabel() {
        return label;
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        canvas = new GamePanel();
        label = new JLabel("");
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        add(canvas, "Center");
        add(label, "North");
        pack();
    }
}
