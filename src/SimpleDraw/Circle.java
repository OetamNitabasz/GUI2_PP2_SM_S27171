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

    public Circle(String text) {
        var elements = text.split(",");
        color = new Color(Integer.parseInt(elements[1]));
        x = Integer.parseInt(elements[2]);
        y = Integer.parseInt(elements[3]);
        r = Integer.parseInt(elements[4]);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Circle,");
        sb.append(color.getRGB()).append(",");
        sb.append(x).append(",").append(y).append(",").append(r);
        return sb.toString();
    }
}
