package sample.map.cell;

import javafx.scene.canvas.GraphicsContext;
import sample.map.snake.Snake;
import sample.misc.IDrawable;
import sample.misc.ImageManager;
import sample.misc.PivotImage;
import sample.misc.Point;

import java.beans.PropertyVetoException;

public abstract class Cell implements IDrawable {
    public static int SCALE = 3;
    public static NullCell NULL = new NullCell();
    protected Point point;
    public Cell(PivotImage image) {
        point = new Point(0, 0);
    }
    public Cell(int row, int column, PivotImage image) {
        this.image = image;
        point = new Point(row, column);
    }
    public Cell(Point position, PivotImage image) {
        this.image = image;
        point = new Point(position.getX(), position.getY());
    }
    private PivotImage image;

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
    public void draw(GraphicsContext graphics, Point origin, int scale) {
        if (image != null) {
            image.draw(graphics, Point.add(origin, Point.mul(point, scale * SCALE * ImageManager.SIZE)), scale * SCALE);
        }
    }

    public Point getPoint() { return point; }

    public abstract boolean checkMove();
    public abstract boolean tryMove(Snake snake);
}
