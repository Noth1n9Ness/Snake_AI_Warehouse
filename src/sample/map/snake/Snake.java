package sample.map.snake;

import jdk.jshell.execution.JdiInitiator;
import sample.map.Direction;
import sample.map.IDirection;
import sample.map.Map;
import sample.misc.Point;

import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private Direction direction;
    private LinkedList<Body> bodies;
    private LinkedList<Point> trail;
    private Map map;
    int earliestTimestamp;
    public Snake(Map map, Point head, Direction direction) {
        this.direction = direction;
        this.map = map;
        bodies = new LinkedList<>();
        bodies.add(new Body(head, earliestTimestamp = 0));
        trail = new LinkedList<>();
    }

    public LinkedList<Body> getBodies() {
        return bodies;
    }

    public LinkedList<Point> getTrail() {
        return trail;
    }

    public Direction getDirection() {
        return direction;
    }
    public void grow() {
        Point point = nextPoint();
        int timestamp = bodies.getFirst().getTimestamp() + 1;
        bodies.addFirst(new Body(point, timestamp));
    }
    public void move() {
        Point point = nextPoint();
        int timestamp = bodies.getFirst().getTimestamp() + 1;
        bodies.addFirst(new Body(point, timestamp));
        trail.addLast(bodies.getLast().getPoint());
        bodies.removeLast();
        earliestTimestamp += 1;
    }
    public boolean execute(LinkedList<IDirection> directions) {
        IDirection first = directions.removeFirst();
        for (IDirection direction : directions) {
            if (!changeDirection(direction.getDirection()) || !map.getCell(nextPoint()).tryMove(this)) return false;
        }
        directions.addFirst(first);
        return true;
    }
    public boolean changeDirection(Direction newDirection) {
        if (direction.getX() + newDirection.getX() == 0 && direction.getY() + newDirection.getY() == 0) return false;
        direction = newDirection;
        return true;
    }
    private Point nextPoint() {
        return Point.add(bodies.getFirst().getPoint(), direction);
    }
}
