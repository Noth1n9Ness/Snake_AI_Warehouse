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
        graphics.setStroke(Color.GRAY);
        super.draw(graphics, origin);
    }
}
