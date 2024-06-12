package SimpleDraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class Canvas extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    Point pointer;
    Pen pen;
    List<Figure> figures = new ArrayList<>();
    DrawingMode mode;
    Color color = Color.GREEN;
    boolean pressedD = false;

    public Canvas() {
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
    }

    public void clear() {
        figures.clear();
        repaint();
    }

    public Color getPickedColor() {
        return color;
    }

    public void setPickedColor(Color color) {
        if(color != null) {
            this.color = color;
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(pressedD){
           /* Figure clicked = null;
            for(int i = figures.size() - 1; i >= 0 ; i--) {
               var f = figures.get(i);
               if(f.contains(e.getPoint())) {
                   clicked = f;
                   break;
               }
            }
           if(clicked != null) {
               pressedD = false;       // confirmationDialog przejmuje Focus przez co canvas nie dostnie informacji o puszczonym klawiszu, musimy recznie przelaczyc pressedD
               var result = JOptionPane.showConfirmDialog(this, "Delete figure?", "Warning", JOptionPane.YES_NO_OPTION);
               if(result == JOptionPane.YES_OPTION) {
                    figures.remove(clicked);
                    repaint();
               }
           }*/
            List<Figure> deleted = figures.stream().filter(s -> s.contains(e.getPoint())).toList();
            if(!deleted.isEmpty()) {
                pressedD = false;       // confirmationDialog przejmuje Focus przez co canvas nie dostnie informacji o puszczonym klawiszu, musimy recznie przelaczyc pressedD
                var result = JOptionPane.showConfirmDialog(this, "Delete figure?", "Warning", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    deleted.forEach(s -> figures.remove(s));
                    repaint();
                }
            }
        } else if (mode == DrawingMode.Pen) {
            var modifier = e.getModifiersEx();
            if ((modifier & MouseEvent.BUTTON1_DOWN_MASK) == MouseEvent.BUTTON1_DOWN_MASK) {
                pointer = e.getPoint();
                pen = new Pen(pointer, color);
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
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            pressedD = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D) {
            pressedD = false;
        }
    }
}
