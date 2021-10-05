package sample.map.cell;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.map.snake.Body;
import sample.map.snake.Snake;
import sample.misc.Point;

public class BodyCell extends Cell {
    private Body body;
    public BodyCell(Body body) {
        super();
        this.body = body;
    }
    public BodyCell(Body body, int row, int column) {
        super(row, column);
        this.body = body;
    }

    @Override
    public void draw(GraphicsContext graphics, Point origin) {
        graphics.setFill(Color.GREEN);
        Point point = Point.sub(Point.add(origin, Point.mul(getPoint(), SIZE)), new Point(-SIZE / 2, -SIZE / 2));
        graphics.fillOval(point.getX(), point.getY(), SIZE, SIZE);
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
