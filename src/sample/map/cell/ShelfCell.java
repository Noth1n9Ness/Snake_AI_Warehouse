package sample.map.cell;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.map.snake.Snake;
import sample.misc.PivotImage;
import sample.misc.Point;

public class ShelfCell extends Cell {
    public ShelfCell(PivotImage image) {
        super(image);
    }
    public ShelfCell(Point point, PivotImage image) {
        super(point.getX(), point.getY(), image);
    }
    public ShelfCell(int row, int column, PivotImage image) {
        super(row, column, image);
    }

    @Override
    public boolean checkMove() {
        return false;
    }

    @Override
    public boolean tryMove(Snake snake) { return false; }
}
