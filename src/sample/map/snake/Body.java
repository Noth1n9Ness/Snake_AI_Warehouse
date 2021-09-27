package sample.map.snake;

import sample.misc.Point;

public class Body {
    private int timestamp;
    private Point point;
    public Body(Point point, int timestamp) {
        this.point = point;
        this.timestamp = timestamp;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public Point getPoint() {
        return point;
    }
}