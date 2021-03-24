package ControlPanel;

import MainFrame.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);

        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
    }

    private void save(ActionEvent e) {
        JFileChooser chooser = new JFileChooser("C:\\Users\\adria\\Desktop\\Lab6Stuff", FileSystemView.getFileSystemView());
        chooser.setFileFilter(
                new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.isDirectory();
                    }

                    @Override
                    public String getDescription() {
                        return "PNG Image (*.png)";
                    }
                }
        );
        int res = chooser.showSaveDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            String newPath = chooser.getSelectedFile().getAbsolutePath();
            if (!chooser.getSelectedFile().getName().toLowerCase().endsWith(".png"))
                newPath += ".png";
            System.out.println(chooser.getSelectedFile().getName());
            try {
                ImageIO.write(frame.getCanvas().getImage(), "PNG", new File(newPath));
            } catch (IOException ex) {
                System.err.println("IO Exception caught when trying to write image in Control Panel!...");
            }
        } else {
            System.out.println("The user cancelled the save option!...");
        }
    }

    private void load(ActionEvent e) {
        JFileChooser chooser = new JFileChooser("C:\\Users\\adria\\Desktop\\Lab6Stuff");
        chooser.setFileFilter(
                new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        String filename = f.getName().toLowerCase();
                        return filename.endsWith(".png");
                    }

                    @Override
                    public String getDescription() {
                        return "PNG Images (*.png)";
                    }
                }
        );
        int res = chooser.showOpenDialog(null);
        BufferedImage image = null;
        if (res == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().exists()) {
            try {
                image = ImageIO.read(new File(chooser.getSelectedFile().getAbsolutePath()));
            } catch (IOException ex) {
                System.err.println("IO Exception caught when trying to read image in Control Panel!...");
            }
            frame.getCanvas().setImage(image);
            if (image != null)
                frame.getCanvas().setGraphics(image.createGraphics());
            frame.getCanvas().repaint();
        } else {
            System.out.println("The user cancelled the load option, or the loading process terminated unsuccessfully!...");
        }
    }

    private void reset(ActionEvent e) {
        BufferedImage image = new BufferedImage(frame.getCanvas().getW(), frame.getCanvas().getH(), BufferedImage.TYPE_INT_ARGB);
        frame.getCanvas().setImage(image);
        Graphics2D newGraphics = image.createGraphics();
        newGraphics.setColor(Color.WHITE);
        newGraphics.fillRect(0, 0, frame.getCanvas().getW(), frame.getCanvas().getH());
        frame.getCanvas().setGraphics(newGraphics);
        frame.getCanvas().repaint();
    }

    private void exit(ActionEvent e) {
        frame.dispose();
    }
}
