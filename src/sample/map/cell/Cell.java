package sample.map.cell;

import javafx.scene.canvas.GraphicsContext;
import sample.map.snake.Snake;
import sample.misc.IDrawable;
import sample.misc.Point;

public abstract class Cell implements IDrawable {
    public static NullCell NULL = new NullCell();
    public static int SIZE = 1;
    private Point point;
    public Cell() {
        point = new Point(0, 0);
    }
    public Cell(int row, int column) {
        point = new Point(row, column);
    }

    public int getRow() { return point.getX(); }
    public int getColumn() {return point.getY(); }
    public Cell setRow(int row) {
        point.setX(row);
        return this;
    }
    public Cell setColumn(int column) {
        point.setY(column);
        return this;
    }

    @Override
    public void draw(GraphicsContext graphics, Point origin) {
        Point point = Point.sub(Point.add(origin, Point.mul(getPoint(), SIZE)), new Point(-SIZE / 2, -SIZE / 2));
        graphics.strokeRect(point.getX(), point.getY(), SIZE, SIZE);
    }

    public Point getPoint() { return point; }

    public abstract boolean checkMove();
    public abstract boolean tryMove(Snake snake);
}
