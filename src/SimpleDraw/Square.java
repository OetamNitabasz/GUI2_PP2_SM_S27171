package SimpleDraw;

import java.awt.*;

public class Square extends Figure{
    int w, x, y;

    public Square(Point pointer) {
        w = 20;
        x = pointer.x - w / 2;
        y = pointer.y - w / 2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, w, w);
    }
}
