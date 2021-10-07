package sample.map.cell;

import javafx.scene.canvas.GraphicsContext;
import sample.map.snake.Snake;
import sample.misc.PivotImage;
import sample.misc.Point;

public class EmptyCell extends Cell {
    public EmptyCell() {
        super(null);
    }
    public EmptyCell(Point point) {
        super(point.getX(), point.getY(), null);
    }
    public EmptyCell(int row, int column) {
        super(row, column, null);
    }


    @Override
    public boolean checkMove() {
        return true;
    }

    @Override
    public boolean tryMove(Snake snake) {
        snake.move();
        return true;
    }
}
