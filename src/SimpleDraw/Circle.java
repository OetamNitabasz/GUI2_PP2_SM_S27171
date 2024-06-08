package SimpleDraw;

import java.awt.*;

public class Circle extends Figure {
    int x, y, r;

    public Circle(Point pointer) {
        r = 10;
        x = pointer.x - r;
        y = pointer.y - r;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, r * 2, r * 2);
    }
}
