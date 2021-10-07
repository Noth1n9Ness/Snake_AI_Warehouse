package sample.map.snake;

import sample.misc.Point;

public class Body {
    private int timestamp;
    private Point point;
    private int flag;
    private String part;
    private Body next;
    private Body previous;
    public Body(Point point, int timestamp) {
        this.point = point;
        this.timestamp = timestamp;
        previous = next = null;
        setFlag();
    }

    public int getTimestamp() {
        return timestamp;
    }

    public Point getPoint() {
        return point;
    }

    public int getFlag() {
        return flag;
    }

    private void setFlag() {
        Point previousPoint = null, nextPoint = null;
        if (previous != null) previousPoint = previous.point;
        if (next != null) nextPoint = next.point;
        flag = getMovementFlag(previousPoint, point, nextPoint);

        if (next == null && previous == null) part = "void";
        else if (next == null) part = "tail";
        else if (previous == null) part = "head";
        else part = "body";
    }

    public void setNext(Body next) {
        this.next = next;
        setFlag();
    }
    public void setPrevious(Body previous) {
        this.previous = previous;
        setFlag();
    }

    public Body getPreviousBody() {
        return previous;
    }
    public Body getNextBody() {
        return next;
    }

    public String getPart() {
        return part;
    }
    public static int getMovementFlag(Point a, Point b, Point c) {
        int flag = 0;
        Point delta;
        if (b == null) return 0;
        if (a != null) {
            delta = Point.sub(a, b);
            if (delta.getX() == -1) flag |= 0b0010;
            else if (delta.getX() == 1) flag |= 0b1000;
            else if (delta.getY() == -1) flag |= 0b0100;
            else if (delta.getY() ==  1) flag |= 0b0001;
        }
        if (c != null) {
            delta = Point.sub(c, b);
            if (delta.getX() == 1) flag |= 0b1000;
            else if (delta.getX() == -1) flag |= 0b0010;
            else if (delta.getY() == 1) flag |= 0b0001;
            else if (delta.getY() == -1) flag |= 0b0100;
        }
        return flag;
    }
}