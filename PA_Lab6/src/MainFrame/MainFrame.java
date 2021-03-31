package MainFrame;

import ConfigPanel.ConfigPanel;
import ControlPanel.ControlPanel;
import DrawingPanel.DrawingPanel;

import javax.swing.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    public DrawingPanel getCanvas() {
        return canvas;
    }

    public ConfigPanel getConfigPanel() {
        return configPanel;
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        canvas = new DrawingPanel(this);
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        add(canvas, "Center");
        add(configPanel, "North");
        add(controlPanel, "South");
        pack();
    }
}
