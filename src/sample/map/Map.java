package sample.map;

import sample.map.cell.*;
import sample.map.snake.Body;
import sample.map.snake.Snake;
import sample.misc.Point;

import java.util.ArrayList;

public class Map {
    private int rowNumber, columnNumber;
    private ArrayList<Cell> cells;

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
                    case 3: cell = new FoodCell(); break;
                    default: cell = new EmptyCell();
                }
                cells.add(cell.setRow(column).setColumn(row));
            }
        }
        return;
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
        return row * columnNumber + column;
    }
    public int convert(Point point) {
        return convert(point.getX(), point.getY());
    }
}
