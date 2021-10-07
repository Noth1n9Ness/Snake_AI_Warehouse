package sample.map.cell;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.map.snake.Snake;
import sample.misc.Point;

public class InventoryCell extends Cell{
    public InventoryCell() {
        super();
    }
    public InventoryCell(int row, int column) {
        super(row, column);
    }

    @Override
    public void draw(GraphicsContext graphics, Point origin) {
        graphics.setFill(Color.RED);
        Point point = Point.sub(Point.add(origin, Point.mul(getPoint(), SIZE)), new Point(-SIZE / 2, -SIZE / 2));
        graphics.fillRect(point.getX(), point.getY(), SIZE, SIZE);
    }

    @Override
    public boolean checkMove() {
        return true;
    }

    @Override
    public boolean tryMove(Snake snake) {
        snake.grow();
        return false;
    }
}
