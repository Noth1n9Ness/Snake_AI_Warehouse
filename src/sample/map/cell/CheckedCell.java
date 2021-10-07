package sample.map.cell;

import javafx.scene.canvas.GraphicsContext;
import sample.map.snake.Snake;
import sample.misc.PivotImage;
import sample.misc.Point;

public class CheckedCell extends Cell{
    private Cell originCell;
    public Cell getOriginCell() {
        return originCell;
    }
    public CheckedCell(int row, int column, Cell originCell, PivotImage image) {
        super(row, column, image);
        this.originCell = originCell;
    }
    public CheckedCell(Point point, Cell origin, PivotImage image) {
        super(point.getX(), point.getY(), image);
        this.originCell = origin;
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
    public void draw(GraphicsContext graphics, Point origin, int scale) {
        this.originCell.draw(graphics, origin, scale);
        super.draw(graphics, origin, scale);
    }
}
