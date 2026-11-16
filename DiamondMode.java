package diamondmod;

import cs.utoronto.utm.assignment2.paint.protocol.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class DiamondMode implements PaintMode {
    static {
        ModeRegistry.registerMode("Diamond", new ModeRegistry.ModeEntry(
                DiamondMode::new,
                new Polygon(10, 0, 20, 10, 10, 20, 0, 10)));
    }

    private Diamond shape;

    public void mousePressed(Point p, PaintModel model) {
        shape = new Diamond(p);
        shape.setColor(model.getColor());
        shape.setFilled(model.isFilled());
        model.addShape(shape);
    }

    public void mouseDragged(Point p)  { if (shape != null) shape.resize(p); }
    public void mouseReleased(Point p) { shape = null; }

    public static class Diamond extends Shape {
        public Diamond(Point origin) { super(origin, Color.BLACK); }

        public void draw(GraphicsContext g) {
            double x = origin.x, y = origin.y, w = width, h = height;
            double[] xs = { x + w / 2, x + w, x + w / 2, x };
            double[] ys = { y, y + h / 2, y + h, y + h / 2 };

            if (isFilled()) { g.setFill(getColor());   g.fillPolygon(xs, ys, 4); }
            else            { g.setStroke(getColor()); g.strokePolygon(xs, ys, 4); }
        }
    }
}
