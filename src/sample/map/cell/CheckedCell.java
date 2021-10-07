package sample.map.cell;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.map.snake.Snake;
import sample.misc.Point;

public class CheckedCell extends Cell{

    public CheckedCell(Point point) {
        super(point.getX(), point.getY());
    }
    @Override
    public boolean checkMove() {
        return false;
    }

    @Override
    public boolean tryMove(Snake snake) {
        return false;
    }

    @Override
    public void draw(GraphicsContext graphics, Point origin) {
        graphics.setFill(Color.GRAY);
        Point point = Point.sub(Point.add(origin, Point.mul(getPoint(), SIZE)), new Point(-SIZE / 2, -SIZE / 2));
        graphics.fillRect(point.getX(), point.getY(), SIZE, SIZE);
    }
}
