package sample.algorithm;

import sample.map.Direction;
import sample.map.IDirection;
import sample.map.Map;
import sample.map.cell.*;
import sample.map.snake.Snake;
import sample.misc.Point;
import sample.misc.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch implements Algorithm{
    private Map map;
    private int columnNumber, rowNumber;
    private Snake snake;
    public BreadthFirstSearch(Map map, Snake snake) {
        this.map = map;
        this.snake = snake;
    }


    private boolean[] visited;
    private LinkedList<IDirection>[] trace;
    private int[] distance;
    private Queue<DirectedCell> queue;
    private ArrayList<Cell> visitedCells;
    private DirectedCell current;
    private int count;
    private boolean found;
    private int timestamp, depth;
    @Override
    public void Start() {
        map.fillSnake(snake);
        columnNumber = map.getColumnNumber();
        rowNumber = map.getRowNumber();
        int totalNumber = columnNumber * rowNumber;
        int index;

        visited = new boolean[totalNumber];
        trace = new LinkedList[totalNumber];
        distance = new int[totalNumber];
        queue = new LinkedList<>();
        visitedCells = new ArrayList<>();
        current = new DirectedCell(map.getCell(snake.getBodies().getFirst().getPoint()), snake.getDirection());
        for (index = 0; index < totalNumber; index++) distance[index] = Integer.MAX_VALUE;
        timestamp = snake.getBodies().getLast().getTimestamp();
        index = map.convert(current.getPoint());
        visited[index] = true;
        queue.add(current);
        trace[index] = new LinkedList<>();
        trace[index].addLast(current);
        distance[index] = 0;

        found = false;
        depth = 0;
        count = 1;
    }

    @Override
    public void Update() {
        int newCount = 0, savedIndex = 0;
        depth++;
        while (count-- > 0 && !queue.isEmpty() && !found) {
            current = queue.remove();
            int currentIndex = map.convert(current.getPoint()), nextIndex;
            for (Direction direction : current.getDirection().getDirections()) {
                DirectedCell next = new DirectedCell(map.getCell(Point.add(current.getPoint(), direction)), direction);
                if (next.checkMove() && !visited[nextIndex = map.convert(next.getPoint())]) {
                    if (next.getCell() instanceof BodyCell) {
                        BodyCell bodyCell = (BodyCell) next.getCell();
                        if (depth <= bodyCell.getBody().getTimestamp() - timestamp) continue;
                    }
                    if (next.getRow() == 0 && next.getColumn() == 0) {
                        next = next;
                    }
                    newCount++;
                    queue.add(next);
                    trace[nextIndex] = Utils.clone(trace[currentIndex]);
                    trace[nextIndex].addLast(next);
                    if (next.getCell() instanceof FoodCell) {
                        found = true;
                        savedIndex = nextIndex;
                        break;
                    }
                    map.getCells().set(nextIndex, new CheckedCell(next.getPoint()));
                    visited[nextIndex] = true;
                    visitedCells.add(next);
                }
            }
        }
        if (!found) count = newCount;
        else {
            map.removeSnake(snake);
            for (Cell cell : visitedCells) {
                Point point = cell.getPoint();
                map.getCells().set(map.convert(point), new EmptyCell(point));
            }
            boolean check = snake.execute(trace[savedIndex]);
            for (Point point : snake.getTrail()) {
                map.getCells().set(map.convert(point), new TrailCell(point));
            }
            Start();
        }
    }
}
