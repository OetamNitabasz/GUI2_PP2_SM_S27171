
package SimpleDraw;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class Window extends JFrame implements ActionListener {

    private final StatusBar statusBar;
    private final Canvas canvas;
    private final MenuBar menuBar;


    public Window() {
        super("Simple Draw");
        statusBar = new StatusBar();
        canvas = new Canvas(statusBar);
        menuBar = new MenuBar(this);
        this.setJMenuBar(menuBar);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.add(canvas, BorderLayout.CENTER);
        this.add(statusBar, BorderLayout.SOUTH);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                quit();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var src = e.getSource();
        if (src == menuBar.open) {
            JFileChooser dialog = new JFileChooser();
            var sge = new FileNameExtensionFilter("pliki graficzne sge", "sge");
            dialog.addChoosableFileFilter(sge);
            dialog.setFileFilter(sge);
            if (dialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                var file = dialog.getSelectedFile();
                canvas.load(file);
                setTitle(file);
            }
        } else if (src == menuBar.circle) {
            statusBar.setMode(e.getActionCommand());
            canvas.setDrawingMode(DrawingMode.Circle);
        } else if (src == menuBar.square) {
            statusBar.setMode(e.getActionCommand());
            canvas.setDrawingMode(DrawingMode.Square);
        } else if (src == menuBar.pen) {
            statusBar.setMode(e.getActionCommand());
            canvas.setDrawingMode(DrawingMode.Pen);
        } else if (src == menuBar.clear) {
            canvas.clear();
        } else if (src == menuBar.color) {
            var c = JColorChooser.showDialog(this, "Choose Color", canvas.getPickedColor());
            canvas.setPickedColor(c);
        } else if (src == menuBar.saveAs) {
            saveAs();
        } else if (src == menuBar.save) {
            save();
        } else if (src == menuBar.quit) {
            quit();
        }
    }

    private boolean save() {
        if (canvas.isNew())
            return saveAs();
        else
            canvas.save();
        return true;
    }

    private boolean saveAs() {
        JFileChooser dialog = new JFileChooser();
        var sge = new FileNameExtensionFilter("pliki graficzne sge", "sge");
        dialog.addChoosableFileFilter(sge);
        dialog.setFileFilter(sge);
        if (dialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            var file = dialog.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith(".sge")) {
                file = new File(file + ".sge");
            }
            if (file.exists()) {
                var result = JOptionPane.showConfirmDialog(this, "Overwrite file?", "File exist", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.NO_OPTION) {
                    return false;
                }
            }
            canvas.saveAs(file);
            setTitle(file);
            return true;
        }
        return false;
    }

    private void quit() {
        if (canvas.isModified()) {
            var result = JOptionPane.showConfirmDialog(this, "Save changes?", "File modified", JOptionPane.YES_NO_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                if (save()) {
                    System.exit(0);
                }
            } else if (result == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }

    private void setTitle(File file) {
        setTitle("Simple Draw: " + file.getName());
    }
}
