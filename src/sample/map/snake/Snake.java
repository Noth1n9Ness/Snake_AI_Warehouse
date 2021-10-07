package sample.map.snake;

import jdk.jshell.execution.JdiInitiator;
import sample.map.Direction;
import sample.map.IDirection;
import sample.map.Map;
import sample.map.cell.TrailCell;
import sample.misc.Point;

import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private Direction direction;
    private LinkedList<Body> bodies;
    private LinkedList<Point> trail;
    private Map map;
    private int count;
    int earliestTimestamp;
    public Snake(Map map, Point head, Direction direction) {
        this.direction = direction;
        this.map = map;
        bodies = new LinkedList<>();
        Body snakeHead = new Body(head, 1);
        Body snakeTail = new Body(Point.sub(head, direction), earliestTimestamp = 0);
        bodies.add(snakeHead);
        bodies.add(snakeTail);
        snakeHead.setNext(snakeTail);
        snakeTail.setPrevious(snakeHead);
        trail = new LinkedList<>();
        trail.addLast(snakeTail.getPoint());
        count = 1;
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
        Body newHead = new Body(point, timestamp);
        bodies.getFirst().setPrevious(newHead);
        newHead.setNext(bodies.getFirst());
        bodies.getFirst().setPrevious(newHead);
        bodies.addFirst(newHead);
    }
    public void move() {
        Point point = nextPoint();
        int timestamp = bodies.getFirst().getTimestamp() + 1;
        Body newHead = new Body(point, timestamp);
        newHead.setNext(bodies.getFirst());
        bodies.getFirst().setPrevious(newHead);
        bodies.addFirst(newHead);
        Body tail = bodies.getLast();
        Body newTail = tail.getPreviousBody();
        newTail.setNext(null);
        bodies.removeLast();
        trail.addLast(bodies.getLast().getPoint());
        count += 1;
    }
    public void clearTrail() {
        if (count > 2) {
            Point a = trail.getLast();
            trail.removeLast();
            Point b = trail.getLast();
            trail.removeLast();
            trail.clear();
            trail.addFirst(a);
            trail.addFirst(b);
        }
    }
    public boolean execute(LinkedList<IDirection> directions) {
        IDirection first = directions.removeFirst();
        for (IDirection direction : directions) {
            if (!changeDirection(direction.getDirection()) || !map.getCell(nextPoint()).tryMove(this)) return false;
        }
        directions.addFirst(first);
        return true;
    }
    public boolean execute(IDirection direction) {
        if (!changeDirection(direction.getDirection()) || !map.getCell(nextPoint()).tryMove(this)) return false;
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
