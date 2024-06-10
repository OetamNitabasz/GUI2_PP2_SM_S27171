package SimpleDraw;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;

enum Status {
    New,
    Modified,
    Saved
}

public class Window extends JFrame implements ActionListener {
    StatusBar statusBar = new StatusBar();
    Canvas canvas = new Canvas();
    MenuBar menuBar = new MenuBar(this);

    public Window() {
        super("Simple Draw");
        this.setJMenuBar(menuBar);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.add(canvas, BorderLayout.CENTER);
        this.add(statusBar, BorderLayout.SOUTH);
        statusBar.setState(Status.New);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var src = e.getSource();
        if (src == menuBar.open) {
            JFileChooser dialog = new JFileChooser();
            var sge = new FileNameExtensionFilter("pliki graficzne sge", "sge");
            dialog.addChoosableFileFilter(sge);
            dialog.setFileFilter(sge);
            dialog.showOpenDialog(this);
        } else if (src == menuBar.circle) {
            statusBar.setMode(e.getActionCommand());
            canvas.setDrawingMode(DrawingMode.Circle);
        } else if(src == menuBar.square) {
            statusBar.setMode(e.getActionCommand());
            canvas.setDrawingMode(DrawingMode.Square);
        } else if(src == menuBar.pen) {
            statusBar.setMode(e.getActionCommand());
            canvas.setDrawingMode(DrawingMode.Pen);
        }
    }
}
