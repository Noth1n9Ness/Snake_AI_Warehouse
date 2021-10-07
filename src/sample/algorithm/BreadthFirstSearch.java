package sample.algorithm;

import sample.map.Direction;
import sample.map.IDirection;
import sample.map.Map;
import sample.map.cell.*;
import sample.map.snake.Body;
import sample.map.snake.Snake;
import sample.misc.ImageManager;
import sample.misc.Point;
import sample.misc.Utils;

import java.util.ArrayList;
import java.util.Iterator;
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
    private Iterator iter;
    private boolean isMoving;
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
        iter = null;
        isMoving = false;
    }

    @Override
    public void Update() {
        if (isMoving) {
            if (iter != null) {
                map.removeSnake(snake);
                map.setTrail();
                if (iter.hasNext()) {
                    snake.execute((IDirection) iter.next());
                    map.fillSnake(snake);
                } else Start();
            }
        }
        if (!isMoving) {
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
                        newCount++;
                        queue.add(next);
                        trace[nextIndex] = Utils.clone(trace[currentIndex]);
                        trace[nextIndex].addLast(next);
                        if (next.getCell() instanceof InventoryCell) {
                            found = true;
                            savedIndex = nextIndex;
                            break;
                        }
                        map.getCells().set(nextIndex, new CheckedCell(next.getPoint(), map.getCell(nextIndex), ImageManager.getPivotImage("checked", 0)));
                        visited[nextIndex] = true;
                        visitedCells.add(next);
                    }
                }
            }
            {
                if (!found) count = newCount;
                else {
//            map.removeSnake(snake);
//            for (Cell cell : visitedCells) {
//                Point point = cell.getPoint();
//                map.getCells().set(map.convert(point), new EmptyCell(point));
//            }
//            snake.execute(trace[savedIndex]);
//            Point previousPoint = null, currentPoint = null;
//            for (Point point : snake.getTrail()) {
//                Cell cell = map.getCell(point);
//                if (!(map.getCell(point) instanceof  TrailCell)) {
//                    map.getCells().set(map.convert(point), new TrailCell(point));
//                }
//                if (currentPoint != null) {
//                    TrailCell trailCell = (TrailCell) map.getCell(currentPoint);
//                    trailCell.addFlag(Body.getMovementFlag(previousPoint, currentPoint, point));
//                    previousPoint = currentPoint;
//                }
//                currentPoint = point;
//            }
//            if (previousPoint != null && currentPoint != null) {
//                TrailCell trailCell = (TrailCell) map.getCell(currentPoint);
//                trailCell.addFlag(Body.getMovementFlag(previousPoint, currentPoint, null));
//                previousPoint = currentPoint;
//            }
//            Start();
                    iter = trace[savedIndex].iterator();
                    iter.next();
                    for (Cell cell : visitedCells) {
                        map.getCells().set(map.convert(cell.getPoint()), new EmptyCell(cell.getRow(), cell.getColumn()));
                    }
                    isMoving = true;
                }
            }
        }
    }
}
