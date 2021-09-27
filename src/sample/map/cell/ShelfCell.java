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
        Point point = Point.sub(Point.add(origin, Point.mul(getPoint(), CELL_SIZE)), new Point(-CELL_SIZE / 2, -CELL_SIZE / 2));
        graphics.fillRect(point.getX(), point.getY(), CELL_SIZE, CELL_SIZE);
    }

    @Override
    public boolean checkMove() {
        return false;
    }

    @Override
    public boolean tryMove(Snake snake) { return false; }
}
