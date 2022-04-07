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

        map = new Map();
        map.setMap(6, 4, 4);
        algorithm = new BreadthFirstSearch(map, map.getSnake());
        //

        this.map = map;
        this.algorithm = algorithm;
        algorithm.Start();
    }
    public void draw(GraphicsContext graphics) {
        graphics.clearRect(0, 0, graphics.getCanvas().getWidth(), graphics.getCanvas().getHeight());
        algorithm.Update();
        map.draw(graphics, Point.ZERO, 1);
    }
}
