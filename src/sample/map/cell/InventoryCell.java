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
        graphics.setStroke(Color.RED);
        super.draw(graphics, origin);
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
