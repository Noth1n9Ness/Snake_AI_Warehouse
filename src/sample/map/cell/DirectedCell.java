package sample.map.cell;

import javafx.scene.canvas.GraphicsContext;
import sample.map.Direction;
import sample.map.IDirection;
import sample.map.snake.Snake;
import sample.misc.Point;

public class DirectedCell extends Cell implements IDirection {
    private Cell cell;
    private Direction direction;
    public DirectedCell(Cell cell, Direction direction) {
        super(cell.getRow(), cell.getColumn());
        this.cell = cell;
        this.direction = direction;
    }
    @Override
    public boolean checkMove() {
        return cell.checkMove();
    }

    @Override
    public boolean tryMove(Snake snake) {
        return cell.tryMove(snake);
    }

    @Override
    public void draw(GraphicsContext graphics, Point origin) {
        cell.draw(graphics, origin);
    }

    public Direction getDirection() {
        return direction;
    }
    public Cell getCell() {
        return cell;
    }
}
