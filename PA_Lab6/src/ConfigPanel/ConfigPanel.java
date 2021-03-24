package ConfigPanel;

import MainFrame.MainFrame;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner sidesField;
    JComboBox<String> colorCombo;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    public int getSidesField() {
        return (int) sidesField.getValue();
    }

    public String getCurrentColor() {
        return (String) colorCombo.getSelectedItem();
    }

    private void init() {
        label = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(6);

        String[] colors = {"RANDOM", "BLACK"};
        colorCombo = new JComboBox<>(colors);
        colorCombo.setSelectedIndex(0);
        add(label);
        add(sidesField);
        add(colorCombo);
    }
}
