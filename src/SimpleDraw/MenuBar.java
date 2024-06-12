package SimpleDraw;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


public class MenuBar extends JMenuBar  {

    final JMenuItem open;
    final JMenuItem save;
    final JMenuItem saveAs;
    final JMenuItem quit;

    final JMenuItem color;
    final JMenuItem clear;
    final JRadioButtonMenuItem circle;
    final JRadioButtonMenuItem square;
    final JRadioButtonMenuItem pen;

    public MenuBar(ActionListener obslugaMenu) {

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveAs = new JMenuItem("Save As");
        quit = new JMenuItem("Quit");

        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        open.setMnemonic(KeyEvent.VK_O);
        open.addActionListener(obslugaMenu);

        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        save.setMnemonic(KeyEvent.VK_S);
        save.addActionListener(obslugaMenu);


        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        saveAs.setMnemonic(KeyEvent.VK_A);
        saveAs.addActionListener(obslugaMenu);


        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        quit.setMnemonic(KeyEvent.VK_Q);
        quit.addActionListener(obslugaMenu);

        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.addSeparator();
        file.add(quit);
        this.add(file);

        JMenu draw = new JMenu("Draw");
        JMenu figure = new JMenu("Figure");
        draw.setMnemonic(KeyEvent.VK_D);
        figure.setMnemonic(KeyEvent.VK_F);

        var grupa = new ButtonGroup();

        circle = new JRadioButtonMenuItem("Circle");
        circle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        circle.setMnemonic(KeyEvent.VK_C);
        circle.addActionListener(obslugaMenu);
        grupa.add(circle);
        figure.add(circle);

        square = new JRadioButtonMenuItem("Square");
        square.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        square.setMnemonic(KeyEvent.VK_R);
        square.addActionListener(obslugaMenu);
        grupa.add(square);
        figure.add(square);

        pen = new JRadioButtonMenuItem("Pen");
        pen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        pen.setMnemonic(KeyEvent.VK_P);
        pen.addActionListener(obslugaMenu);
        grupa.add(pen);
        figure.add(pen);

        draw.add(figure);

        color = new JMenuItem("Color");
        color.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        color.setMnemonic(KeyEvent.VK_C);
        color.addActionListener(obslugaMenu);
        draw.add(color);
        draw.addSeparator();

        clear = new JMenuItem("Clear");
        clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        clear.setMnemonic(KeyEvent.VK_L);
        clear.addActionListener(obslugaMenu);
        draw.add(clear);


        this.add(draw);
    }
}
