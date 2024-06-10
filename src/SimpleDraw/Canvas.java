package SimpleDraw;

import javax.swing.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class Canvas extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    Point pointer;
    Pen pen;
    List<Figure> figures = new ArrayList<>();
    DrawingMode mode;


    public Canvas() {
        setBackground(Color.magenta);
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(mode == DrawingMode.Pen) {
        var modifier = e.getModifiersEx();
        if((modifier & MouseEvent.BUTTON1_DOWN_MASK) == MouseEvent.BUTTON1_DOWN_MASK) {
            pointer = e.getPoint();
            pen = new Pen(pointer);
            figures.add(pen);
        }
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

    private void drawCircle() {
        if(pointer == null)
            return;
        figures.add(new Circle(pointer));
        repaint();
    }

    private void drawSquare() {
        if(pointer == null)
            return;
        figures.add(new Square(pointer));
        repaint();
    }

    public void setDrawingMode(DrawingMode mode) {
        this.mode = mode;
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
        pointer = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       pointer = e.getPoint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F1) {
            if(mode == DrawingMode.Square) {
                drawSquare();
            } else if(mode == DrawingMode.Circle) {
                drawCircle();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
