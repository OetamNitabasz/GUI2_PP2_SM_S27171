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

    public Square(String text) {
        var elements = text.split(",");
        color = new Color(Integer.parseInt(elements[1]));
        x = Integer.parseInt(elements[2]);
        y = Integer.parseInt(elements[3]);
        w = Integer.parseInt(elements[4]);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Square,");
        sb.append(color.getRGB()).append(",");
        sb.append(x).append(",").append(y).append(",").append(w);
        return sb.toString();
    }
}
