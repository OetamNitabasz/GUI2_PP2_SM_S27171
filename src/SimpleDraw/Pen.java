package SimpleDraw;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Pen extends Figure {
    int x, y, updatedX, updatedY;

    public Pen(Point pointer) {
        x = (int) pointer.getX();
        y = (int) pointer.getY();
        updatedX = (int) pointer.getX();
        updatedY = (int) pointer.getY();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x,y, updatedX, updatedY);
    }
    public void update(Point pointer) {
        updatedX = (int) pointer.getX();
        updatedY = (int) pointer.getY();
    }
}
