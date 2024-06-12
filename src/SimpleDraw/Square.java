package SimpleDraw;

import java.awt.*;

public class Square extends Figure{
    int w, x, y;

    public Square(Point pointer) {
        w = 20;
        x = pointer.x - w / 2;
        y = pointer.y - w / 2;
        color = colorGenerator();
    }
    @Override
    public boolean contains(Point pointer) {
        return (pointer.x >= x && pointer.x <= x + w && pointer.y >= y && pointer.y <= y + w);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, w, w);
    }
}
