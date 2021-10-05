package sample.map.cell;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.map.snake.Snake;
import sample.misc.Point;

public class ShelfCell extends Cell {
    public ShelfCell() {
        super();
    }
    public ShelfCell(int row, int column) {
        super(row, column);
    }

    @Override
    public void draw(GraphicsContext graphics, Point origin) {
        graphics.setFill(Color.BROWN);
        Point point = Point.sub(Point.add(origin, Point.mul(getPoint(), SIZE)), new Point(-SIZE / 2, -SIZE / 2));
        graphics.fillRect(point.getX() + 1, point.getY() + 1, SIZE - 2, SIZE - 2);
    }

    @Override
    public boolean checkMove() {
        return false;
    }

    @Override
    public boolean tryMove(Snake snake) { return false; }
}
