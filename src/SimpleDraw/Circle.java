package SimpleDraw;

import java.awt.*;

public class Circle extends Figure {
    int x, y, r;

    public Circle(Point pointer) {
        r = 10;
        x = pointer.x - r;
        y = pointer.y - r;
        color = colorGenerator();
    }
    @Override
    public boolean contains(Point pointer) {
        var xC = x + r;
        var yC = y + r;

        var dX = Math.abs(xC - pointer.x);
        var dY = Math.abs(yC - pointer.y);

        var d = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));

        return d <= r;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, r * 2, r * 2);
    }
}
