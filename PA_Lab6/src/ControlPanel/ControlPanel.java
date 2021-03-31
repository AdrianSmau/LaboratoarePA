package ControlPanel;

import DataStoring.DataStore;
import MainFrame.MainFrame;
import main.myAntlr.Launch;
import main.myAntlr.returnTypes.DrawCmdReturn;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton deleteBtn = new JButton("Delete");
    JButton resetBtn = new JButton("Reset");
    JButton freeHandBtn = new JButton("FreeHand");
    JButton exitBtn = new JButton("Exit");
    JButton submitBtn = new JButton("Submit");
    JTextArea textArea = new JTextArea("Enter your command here!...");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);

        textArea.setFont(new Font("SansSerif", Font.PLAIN, 15));
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.weightx = 0.0;
        gridbag.setConstraints(textArea, c);
        add(textArea);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(submitBtn, c);
        add(submitBtn);

        c.gridwidth = 1;
        c.weightx = 1.0;
        gridbag.setConstraints(saveBtn, c);
        add(saveBtn);
        gridbag.setConstraints(loadBtn, c);
        add(loadBtn);
        gridbag.setConstraints(deleteBtn, c);
        add(deleteBtn);
        gridbag.setConstraints(resetBtn, c);
        add(resetBtn);
        gridbag.setConstraints(freeHandBtn, c);
        add(freeHandBtn);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(exitBtn, c);
        add(exitBtn);

        submitBtn.addActionListener(this::submit);
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        deleteBtn.addActionListener(this::delete);
        resetBtn.addActionListener(this::reset);
        freeHandBtn.addActionListener(this::freeHand);
        exitBtn.addActionListener(this::exit);
    }

    private void submit(ActionEvent e) {
        String input = textArea.getText();
        textArea.setText("Enter your command here!...");
        input = input.replace("\n", "");
        Launch app = new Launch();
        Object y = app.parse(input);
        if (y == null) {
            System.out.println("Unrecognized command! Please try again!...");
        }
        if (y instanceof Integer[]) {
            this.frame.getCanvas().deleteByCommand(((Integer[]) y)[0], ((Integer[]) y)[1]);
        }
        if (y instanceof DrawCmdReturn) {
            this.frame.getCanvas().drawByCommand(((DrawCmdReturn) y));
        }
        if (y instanceof String) {
            switch ((String) y) {
                case "reset" -> reset(e);
                case "freehand" -> freeHand(e);
                case "exit" -> exit(e);
            }
        }
    }

    private void save(ActionEvent e) {
        if (this.frame.getCanvas().isDeleteMode()) {
            this.frame.getCanvas().setDeleteMode(false);
            this.frame.getCanvas().deactivateMode();
        }
        if (this.frame.getCanvas().isFreeHandMode()) {
            this.frame.getCanvas().setFreeHandMode(false);
            this.frame.getCanvas().deactivateMode();
        }
        JFileChooser chooser = new JFileChooser("C:\\Users\\adria\\Desktop\\Lab6Stuff", FileSystemView.getFileSystemView());
        chooser.setFileFilter(
                new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.isDirectory();
                    }

                    @Override
                    public String getDescription() {
                        return "Directory Folder";
                    }
                }
        );
        int res = chooser.showSaveDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            String newPathSer = chooser.getSelectedFile().getAbsolutePath();
            String newPathPng = newPathSer;
            if (!chooser.getSelectedFile().getName().toLowerCase().endsWith(".png")) {
                newPathPng += ".png";
                newPathSer += ".ser";
            } else {
                newPathSer = newPathSer.substring(0, newPathSer.length() - 4);
                newPathSer += ".ser";
            }
            System.out.println(chooser.getSelectedFile().getName());
            try {
                ImageIO.write(frame.getCanvas().getImage(), "PNG", new File(newPathPng));
            } catch (IOException ex) {
                System.err.println("IO Exception caught when trying to write image in Control Panel!...");
            }
            try (FileOutputStream fos = new FileOutputStream(newPathSer)) {
                var out = new ObjectOutputStream(fos);
                out.writeObject(this.frame.getCanvas().getMyData());
                out.flush();
            } catch (IOException ex) {
                System.err.println("IOException found then Saving the current Canvas!...");
            }
        } else {
            System.out.println("The user cancelled the save option!...");
        }
    }

    private void load(ActionEvent e) {
        if (this.frame.getCanvas().isDeleteMode()) {
            this.frame.getCanvas().setDeleteMode(false);
            this.frame.getCanvas().deactivateMode();
        }
        if (this.frame.getCanvas().isFreeHandMode()) {
            this.frame.getCanvas().setFreeHandMode(false);
            this.frame.getCanvas().deactivateMode();
        }
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
        if (res == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().exists()) {
            String serPath = chooser.getSelectedFile().getAbsolutePath();
            serPath = serPath.substring(0, serPath.length() - 4) + ".ser";
            DataStore importedData = new DataStore();
            try (FileInputStream fis = new FileInputStream(serPath)) {
                var in = new ObjectInputStream(fis);
                importedData = (DataStore) in.readObject();
            } catch (IOException ex) {
                System.err.println("IOException caught when trying to Load the canvas at: " + serPath);
            } catch (ClassNotFoundException ex) {
                System.err.println("ClassNotFoundException caught when trying to Load the canvas at: " + serPath);
            }
            if (importedData != null) {
                this.reset(e);
                this.frame.getCanvas().getMyData().setShapes(importedData.getShapes());
                this.frame.getCanvas().getMyData().setShapeColorMap(importedData.getShapeColorMap());
            }
            frame.getCanvas().repaint();
        } else {
            System.out.println("The user cancelled the load option, or the loading process terminated unsuccessfully!...");
        }
    }

    private void delete(ActionEvent e) {
        if (this.frame.getCanvas().isFreeHandMode()) {
            this.frame.getCanvas().setFreeHandMode(false);
            this.frame.getCanvas().deactivateMode();
        }
        if (this.frame.getCanvas().isDeleteMode()) {
            this.frame.getCanvas().setDeleteMode(false);
            this.frame.getCanvas().deactivateMode();
        } else {
            this.frame.getCanvas().setDeleteMode(true);
            this.frame.getCanvas().activateDeleteMode();
        }
    }

    private void reset(ActionEvent e) {
        if (this.frame.getCanvas().isDeleteMode()) {
            this.frame.getCanvas().setDeleteMode(false);
            this.frame.getCanvas().deactivateMode();
        }
        if (this.frame.getCanvas().isFreeHandMode()) {
            this.frame.getCanvas().setFreeHandMode(false);
            this.frame.getCanvas().deactivateMode();
        }
        BufferedImage image = new BufferedImage(frame.getCanvas().getW(), frame.getCanvas().getH(), BufferedImage.TYPE_INT_ARGB);
        frame.getCanvas().setImage(image);
        Graphics2D newGraphics = image.createGraphics();
        newGraphics.setColor(Color.WHITE);
        newGraphics.fillRect(0, 0, frame.getCanvas().getW(), frame.getCanvas().getH());
        newGraphics.setPaint(Color.BLACK);
        frame.getCanvas().setGraphics(newGraphics);
        this.frame.getCanvas().getMyData().setShapes(new ArrayList<>());
        this.frame.getCanvas().getMyData().setShapeColorMap(new HashMap<>());
        frame.getCanvas().repaint();
    }

    private void freeHand(ActionEvent e) {
        if (this.frame.getCanvas().isDeleteMode()) {
            this.frame.getCanvas().setDeleteMode(false);
            this.frame.getCanvas().deactivateMode();
        }
        if (this.frame.getCanvas().isFreeHandMode()) {
            this.frame.getCanvas().setFreeHandMode(false);
            this.frame.getCanvas().deactivateMode();
        } else {
            this.frame.getCanvas().setFreeHandMode(true);
            this.frame.getCanvas().activateFreeHandMode();
        }
    }

    private void exit(ActionEvent e) {
        frame.dispose();
    }
}
