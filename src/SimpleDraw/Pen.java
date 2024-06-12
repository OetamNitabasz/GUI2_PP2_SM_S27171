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

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        //points.stream().forEach(p -> g.drawOval(p.x, p.y, 2, 2));
        //int[] x;
        var x = points.stream().mapToInt(p -> p.x).toArray();
        var y = points.stream().mapToInt(p -> p.y).toArray();
        var g2 = (Graphics2D) g;
        var s = g2.getStroke();
        g2.setStroke(new BasicStroke(2));
        g.drawPolyline(x,y, points.size());
        g2.setStroke(s);

    }
    // poprawic
    public void update(Point pointer) {
        points.add(pointer);
    }
}
