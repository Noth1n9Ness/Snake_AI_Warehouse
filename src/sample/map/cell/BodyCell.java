package sample.map.cell;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.map.snake.Body;
import sample.map.snake.Snake;
import sample.misc.PivotImage;
import sample.misc.Point;

public class BodyCell extends Cell {
    private Body body;
    public BodyCell(Body body, int row, int column, PivotImage image) {
        super(row, column, image);
        this.body = body;
    }
    public BodyCell(Body body, Point position, PivotImage image) {
        super(position, image);
        this.body = body;
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

    public Body getBody() {
        return body;
    }
}
