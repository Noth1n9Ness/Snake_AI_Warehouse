package sample;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;

public class BFS implements Algorithm{
    boolean[][] visited;
    int[][] distance;
    LinkedList<Cell>[][] trace;
    private LinkedList<Cell> queue;
    private final Set<CellType> MOVEABLECELL = Set.of(
            CellType.EMPTY, CellType.FOOD, CellType.ITEM
    );

    @Override
    public LinkedList<Cell> generate(Map map, Snake snake) {
        visited = new boolean[map.ROW_COUNT][map.COL_COUNT];
        trace = new LinkedList[map.ROW_COUNT][map.COL_COUNT];
        distance = new int[map.ROW_COUNT][map.COL_COUNT];
        queue = new LinkedList<Cell>();
        for (int i = 0; i < map.ROW_COUNT; i++) {
            for (int j = 0;j < map.COL_COUNT; j++)
                distance[i][j] = Integer.MAX_VALUE;
        }

        Cell current = snake.getHead();
        Cell destination = map.getFood();
        visited[current.getRow()][current.getCol()] = true;
        queue.addFirst(current);
        trace[current.getRow()][current.getCol()] = new LinkedList<>();
        trace[current.getRow()][current.getCol()].addLast(current);
        distance[current.getRow()][current.getCol()] = 0;

        while (!queue.isEmpty() && !visited[destination.getRow()][destination.getCol()]) {
            current = queue.removeFirst();
            for(Direction direction: Direction.values()) {
                Cell moveToCell = current.moveTo(direction);
                if (map.checkValidCell(moveToCell) && !visited[moveToCell.getRow()][moveToCell.getCol()]) {
                    queue.addLast(moveToCell);
                    visited[moveToCell.getRow()][moveToCell.getCol()] = true;
                    trace[moveToCell.getRow()][moveToCell.getCol()] = new LinkedList<>();
                    trace[moveToCell.getRow()][moveToCell.getCol()] = this.cloneAndAddCellTrace(trace[current.getRow()][current.getCol()], moveToCell);
                }
            }
        }
        if (!visited[destination.getRow()][destination.getCol()]) {
            return null;
        } else {
            return trace[destination.getRow()][destination.getCol()];
        }
    }

    public LinkedList<Cell> cloneAndAddCellTrace(LinkedList<Cell> trace, Cell cell) {
        LinkedList<Cell> newList =  (LinkedList<Cell>) trace.clone();
        newList.addLast(cell);
        return newList;
    }
}
