package SimpleDraw;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pen extends Figure {
    List<Point> points = new ArrayList<>();

    public Pen(Point pointer, Color color) {
        points.add(pointer);
        this.color = color;
    }

    public Pen(String text) {
        var elements = text.split(",");
        color = new Color(Integer.parseInt(elements[1]));
        for(int i = 2; i < elements.length; i += 2) {
            var x = Integer.parseInt(elements[i]);
            var y = Integer.parseInt(elements[i + 1]);
            points.add(new Point(x, y));
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        var x = points.stream().mapToInt(p -> p.x).toArray();
        var y = points.stream().mapToInt(p -> p.y).toArray();
        var g2 = (Graphics2D) g;
        var s = g2.getStroke();
        g2.setStroke(new BasicStroke(2));
        g.drawPolyline(x,y, points.size());
        g2.setStroke(s);

    }

    public void update(Point pointer) {
        points.add(pointer);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pen,");
        sb.append(color.getRGB());
        points.forEach(p -> sb.append(",").append(p.x).append(",").append(p.y));
        return sb.toString();
    }

}
