package sample.graphics;

import javafx.scene.canvas.GraphicsContext;
import sample.algorithm.Algorithm;
import sample.algorithm.BreadthFirstSearch;
import sample.map.Direction;
import sample.map.cell.Cell;
import sample.map.Map;
import sample.map.snake.Snake;
import sample.misc.Point;

import java.util.ArrayList;

public class Visualizer {
    private Map map;
    private Algorithm algorithm;
    private Snake snake;
    public Visualizer(Map map, Algorithm algorithm) {
        Cell.CELL_SIZE = 20;

        // TEST
        int[][] initMap = new int[][]{
                {0, 0, 3, 0, 0, 0, 0, 0, 0, 3},
                {0, 0, 0, 0, 0, 2, 2, 0, 3, 0},
                {0, 0, 3, 3, 3, 3, 3, 0, 0, 0},
                {0, 0, 0, 0, 2, 2, 3, 0, 0, 0},
                {0, 0, 3, 2, 2, 2, 3, 0, 3, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 3, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 3, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 0, 0, 0},
                {3, 2, 0, 0, 0, 0, 0, 0, 0, 0},
                {2, 2, 2, 2, 2, 2, 3, 2, 2, 2},
                {0, 0, 0, 0, 0, 2, 0, 0, 0, 0},
                {0, 0, 0, 3, 0, 2, 0, 2, 3, 0},
                {0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        map = new Map();
        map.setCells(initMap);
        algorithm = new BreadthFirstSearch(map, snake = new Snake(map, new Point(1, 3), Direction.UP));
        //

        this.map = map;
        this.algorithm = algorithm;
        algorithm.Start();
    }
    public void draw(GraphicsContext graphics) {
        graphics.clearRect(0, 0, graphics.getCanvas().getWidth(), graphics.getCanvas().getHeight());
        algorithm.Update();
        Point origin = Point.ZERO;
        ArrayList<Cell> cells = map.getCells();
        for (Cell cell : cells) cell.draw(graphics, origin);
    }
}
