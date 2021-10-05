package sample.map.cell;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.misc.Point;

public class TrailCell extends EmptyCell{

    public TrailCell(Point point) {
        super(point.getX(), point.getY());
    }

    @Override
    public void draw(GraphicsContext graphics, Point origin) {
        graphics.setFill(Color.GREEN);
        Point point = Point.sub(Point.add(origin, Point.mul(getPoint(), SIZE)), new Point(-SIZE / 2, -SIZE / 2));
        graphics.fillRect(point.getX(), point.getY(), SIZE, SIZE);
    }
}
