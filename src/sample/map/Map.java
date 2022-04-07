package sample.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.map.cell.*;
import sample.map.snake.Body;
import sample.map.snake.Snake;
import sample.misc.IDrawable;
import sample.misc.ImageManager;
import sample.misc.PivotImage;
import sample.misc.Point;

import java.util.*;

public class Map implements IDrawable {
    private int rowNumber, columnNumber, totalNumber;
    private ArrayList<Cell> cells;
    private Dictionary<Integer, TrailCell> trailCells;
    private Snake snake;

    public Snake getSnake() {
        return snake;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }
    public Cell getCell(Point point) {
        if (check(point)) return cells.get(convert(point));
        else return Cell.NULL;
    }
    public Cell getCell(int index) {
        return cells.get(index);
    }

    public Map() {
        rowNumber = 0;
        columnNumber = 0;
    }
    public void setMap(int shelfNumber, int blockNumber, int shelfLength) {
        rowNumber = 2 + 3 * shelfNumber;
        columnNumber = 1 + (shelfLength + 2) * blockNumber - 1;
        totalNumber = rowNumber * columnNumber;
        cells = new ArrayList<>(Collections.nCopies(totalNumber, null));
        trailCells = new Hashtable<>();
        for (int block = 0; block < blockNumber; block++) {
            for (int shelf = 0; shelf < shelfNumber; shelf++) {
                for (int shelfIndex = 0; shelfIndex < shelfLength; shelfIndex++) {
                    int row = 2 + 3 * shelf;
                    int column = 2 + (shelfLength + 2) * block - 1 + shelfIndex;
                    int flag;
                    if (shelfIndex == 0) flag = 0b0010;
                    else if (shelfIndex == shelfLength - 1) flag = 0b1000;
                    else flag = 0b1010 | (Math.random() < 0.5 ? 0b0100 : 0b0000);
                    cells.set(convert(column, row), new ShelfCell(column, row, ImageManager.getPivotImage("shelf", flag)));
                    int random = (int) (Math.random()*100);
                    if (random <= 22) {
                        cells.set(convert(column, row + 1), new InventoryCell(column, row + 1, ImageManager.getPivotImage("inventory", 0)));
                    } else if (random <= 44) {
                        cells.set(convert(column, row -1 ), new InventoryCell(column, row -1, ImageManager.getPivotImage("inventory", 0)));
                    } else if (random <= 50 ) {
                        cells.set(convert(column, row + 1), new InventoryCell(column, row + 1, ImageManager.getPivotImage("inventory", 0)));
                        cells.set(convert(column, row -1 ), new InventoryCell(column, row -1, ImageManager.getPivotImage("inventory", 0)));
                    }
                }
            }
        }
        for (int index = 0; index < totalNumber; index++) {
            if ((cells.get(index) == null)) cells.set(index, new EmptyCell(index / rowNumber, index % rowNumber));
        }
        snake = new Snake(this, new Point(1, rowNumber - 1), Direction.RIGHT);

    }
    public void fillSnake(Snake snake) {
        for (Body body : snake.getBodies()) {
            Point point = body.getPoint();
            cells.set(convert(point.getX(), point.getY()), new BodyCell(body, point.getX(), point.getY(), ImageManager.getPivotImage(body.getPart(), body.getFlag())));
        }
    }
    public void removeSnake(Snake snake) {
        for (Body body : snake.getBodies()) {
            Point point = body.getPoint();
            cells.set(convert(point.getX(), point.getY()), new EmptyCell(point.getX(), point.getY()));
        }
    }
    public boolean check(int column, int row) { return column >= 0 && column < columnNumber && row >= 0 && row < rowNumber; }
    public boolean check(Point point) { return check(point.getX(), point.getY()); }
    public boolean check(int index) {return check(index / rowNumber, index % rowNumber); }
    public int convert(int column, int row) {
        return column * rowNumber + row;
    }
    public int convert(Point point) {
        return convert(point.getX(), point.getY());
    }

    @Override
    public void draw(GraphicsContext graphics, Point origin, int scale) {
//        for (int column = 0; column <= columnNumber; column++) {
//            graphics.setStroke(Color.GRAY);
//
//            // TODO: ADD ORIGIN
//            graphics.strokeLine((column - 0.5) * Cell.SIZE, 0.5 * Cell.SIZE, (column - 0.5) * Cell.SIZE, (rowNumber + 0.5) * Cell.SIZE);
//        }
//        for (int row = 0; row <= rowNumber + 1; row++) {
//            graphics.setStroke(Color.GRAY);
//
//            // TODO: ADD ORIGIN
//            graphics.strokeLine(0.5 * Cell.SIZE, (row - 0.5) * Cell.SIZE, (columnNumber - 0.5) * Cell.SIZE, (row - 0.5) * Cell.SIZE);
//        }
        origin = new Point(20, 40);
        for (int column = 0; column < columnNumber; column++) {
            int columnFlag = (column == 0) ? 0b0100 : (column == columnNumber - 1) ? 0b0001 : 0b0101;
            for (int row = 0; row < rowNumber; row++) {
                int rowFlag = (row == 0) ? 0b0010 : (row == rowNumber - 1) ? 0b1000 : 0b1010;
                PivotImage image = ImageManager.getPivotImage("floor", columnFlag | rowFlag);
                image.draw(graphics, Point.add(origin, Point.mul(new Point(column, row), Cell.SCALE * ImageManager.SIZE)), Cell.SCALE);
            }
        }
        Enumeration enumeration = trailCells.elements();
        while (enumeration.hasMoreElements()) {
            TrailCell trailCell = (TrailCell) enumeration.nextElement();
            trailCell.draw(graphics, origin, scale);
        }
        for (Cell cell : cells) {
            // if (cell instanceof ShelfCell) continue;
            cell.draw(graphics, origin, scale);
        }
    }
    public void setTrail() {
        Point previousPoint = null, currentPoint = null;
        for (Point point : snake.getTrail()) {
            int index = convert(point);
            TrailCell trailCell = trailCells.get(index);
            if (trailCell == null) {
                trailCells.put(index, new TrailCell(point));
            }
            if (currentPoint != null) {
                trailCell = trailCells.get(convert(currentPoint));
                trailCell.addFlag(Body.getMovementFlag(previousPoint, currentPoint, point));
                previousPoint = currentPoint;
            }
            currentPoint = point;
        }
        if (previousPoint != null && currentPoint != null) {
            TrailCell trailCell = trailCells.get(convert(currentPoint));
            trailCell.addFlag(Body.getMovementFlag(previousPoint, currentPoint, null));
        }
        snake.clearTrail();
    }
}
