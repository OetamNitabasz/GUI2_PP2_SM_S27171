package SimpleDraw;

import java.awt.*;
import java.util.Random;

public abstract class Figure {
    private static final Random random = new Random(System.currentTimeMillis());

    protected Color color;
    public abstract void draw(Graphics g);
    public Color colorGenerator() {
        var r = random.nextFloat();
        var g = random.nextFloat();
        var b = random.nextFloat();

        return new Color(r,g,b);
    }
    public boolean contains(Point pointer) {
        return false;
    }
}
