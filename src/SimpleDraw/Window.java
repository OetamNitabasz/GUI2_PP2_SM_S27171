package SimpleDraw;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

enum Status {
    New,
    Modified,
    Saved
}

public class Window extends JFrame implements ActionListener, KeyListener {
    MenuBar menuBar = new MenuBar(this);
    StatusBar statusBar = new StatusBar();
    Canvas canvas = new Canvas(statusBar);

    public Window() {
        super("Simple Draw");
        this.setJMenuBar(menuBar);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.add(canvas, BorderLayout.CENTER);
        this.add(statusBar, BorderLayout.SOUTH);
        statusBar.setState(Status.New);
        addKeyListener(this);
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
        } else if (src == menuBar.circle || src == menuBar.square || src == menuBar.pen) {
            statusBar.setMode(e.getActionCommand());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F1) {
            if(menuBar.circle.isSelected()) {
                canvas.drawCircle();
            } else if(menuBar.square.isSelected()) {
                canvas.drawRect();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
