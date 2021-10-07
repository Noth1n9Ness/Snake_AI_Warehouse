package sample.graphics;

import javafx.scene.canvas.GraphicsContext;
import sample.algorithm.Algorithm;
import sample.algorithm.BreadthFirstSearch;
import sample.map.cell.Cell;
import sample.map.Map;
import sample.map.snake.Snake;
import sample.misc.Point;

public class Visualizer {
    private Map map;
    private Algorithm algorithm;
    private Snake snake;
    public Visualizer(Map map, Algorithm algorithm) {
        Cell.SIZE = 20;

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
        map.setMap(7, 2, 8);
        algorithm = new BreadthFirstSearch(map, map.getSnake());
        //

        this.map = map;
        this.algorithm = algorithm;
        algorithm.Start();
    }
    public void draw(GraphicsContext graphics) {
        graphics.clearRect(0, 0, graphics.getCanvas().getWidth(), graphics.getCanvas().getHeight());
        algorithm.Update();
        map.draw(graphics, Point.ZERO);
    }
}
