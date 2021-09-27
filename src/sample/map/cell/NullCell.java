package sample.map.cell;

import javafx.scene.canvas.GraphicsContext;
import sample.map.snake.Snake;
import sample.misc.Point;

public class NullCell extends Cell{
    protected NullCell() { }
    @Override
    public void draw(GraphicsContext graphics, Point origin) {

    }
    @Override
    public boolean checkMove() {
        return false;
    }

    @Override
    public boolean tryMove(Snake snake) { return false; }
}
