package SimpleDraw;

import javax.swing.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
    Point pointer;
    Pen pen;
    List<Figure> figures = new ArrayList<>();


    public Canvas(StatusBar statusBar) {
        setBackground(Color.magenta);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
       /* var modifier = e.getModifiersEx();
        if((modifier & MouseEvent.BUTTON3_DOWN_MASK) == MouseEvent.BUTTON3_DOWN_MASK) {
            statusBar.mouseCoords(e.getX(), e.getY());
        }*/

        var modifier = e.getModifiersEx();
        if((modifier & MouseEvent.BUTTON1_DOWN_MASK) == MouseEvent.BUTTON1_DOWN_MASK) {
            pointer = e.getPoint();
            pen = new Pen(pointer);
            figures.add(pen);
    }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pen = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        pointer = null;
    }

    public void drawCircle() {
        if(pointer == null)
            return;
        figures.add(new Circle(pointer));
        repaint();
    }

    public void drawRect() {
        if(pointer == null)
            return;
        figures.add(new Square(pointer));
        repaint();
    }

    public void drawPen() {
        if(pointer == null)
            return;
        figures.add(new Pen(pointer));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        figures.forEach(f -> f.draw(g));
    }



    @Override
    public void mouseDragged(MouseEvent e) {
        if(pen != null) {
            pen.update(e.getPoint());
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        pointer = e.getPoint();
    }
}
