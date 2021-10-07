package sample.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.map.cell.*;
import sample.map.snake.Body;
import sample.map.snake.Snake;
import sample.misc.IDrawable;
import sample.misc.Point;

import java.util.ArrayList;
import java.util.Collections;

public class Map implements IDrawable {
    private int rowNumber, columnNumber, totalNumber;
    private ArrayList<Cell> cells;
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

    public Map() {
        rowNumber = 0;
        columnNumber = 0;
    }
    public void setCells(int[][] initMap) {
        columnNumber = initMap[0].length;
        rowNumber = initMap.length;
        cells = new ArrayList<>();
        for (int row = 0; row < rowNumber; row++) {
            for (int column = 0; column < columnNumber; column++) {
                Cell cell;
                switch (initMap[row][column]) {
                    // case 1: cell = new BodyCell(); break;
                    case 2: cell = new ShelfCell(); break;
                    case 3: cell = new InventoryCell(); break;
                    default: cell = new EmptyCell();
                }
                cells.add(cell.setRow(column).setColumn(row));
            }
        }
        return;
    }
    public void setMap(int shelfNumber, int blockNumber, int shelfLength) {
        rowNumber = 2 + 3 * shelfNumber;
        columnNumber = 2 + (shelfLength + 2) * blockNumber - 1;
        totalNumber = rowNumber * columnNumber;
        cells = new ArrayList<>(Collections.nCopies(totalNumber, null));
        for (int block = 0; block < blockNumber; block++) {
            for (int shelf = 0; shelf < shelfNumber; shelf++) {
                for (int shelfIndex = 0; shelfIndex < shelfLength; shelfIndex++) {
                    int row = 2 + 3 * shelf;
                    int column = 2 + (shelfLength + 2) * block - 1 + shelfIndex;
                    cells.set(convert(column, row), new ShelfCell(column, row));
                    int random = (int) (Math.random()*100);
                    if (random <= 22) {
                        cells.set(convert(column, row + 1), new InventoryCell(column, row + 1));
                    } else if (random <= 44) {
                        cells.set(convert(column, row -1 ), new InventoryCell(column, row -1));
                    } else if (random <= 50 ) {
                        cells.set(convert(column, row + 1), new InventoryCell(column, row + 1));
                        cells.set(convert(column, row -1 ), new InventoryCell(column, row -1));
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
            cells.set(convert(point.getX(), point.getY()), new BodyCell(body, point.getX(), point.getY()));
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
    public int convert(int column, int row) {
        return column * rowNumber + row;
    }
    public int convert(Point point) {
        return convert(point.getX(), point.getY());
    }

    @Override
    public void draw(GraphicsContext graphics, Point origin) {
        for (int column = 0; column <= columnNumber; column++) {
            graphics.setStroke(Color.GRAY);

            // TODO: ADD ORIGIN
            graphics.strokeLine((column - 0.5) * Cell.SIZE, 0.5 * Cell.SIZE, (column - 0.5) * Cell.SIZE, (rowNumber + 0.5) * Cell.SIZE);
        }
        for (int row = 0; row <= rowNumber + 1; row++) {
            graphics.setStroke(Color.GRAY);

            // TODO: ADD ORIGIN
            graphics.strokeLine(0.5 * Cell.SIZE, (row - 0.5) * Cell.SIZE, (columnNumber - 0.5) * Cell.SIZE, (row - 0.5) * Cell.SIZE);
        }
        for (Cell cell : cells) cell.draw(graphics, origin);
    }
}
