package sample.misc;

public class Point {
    private int x, y;
    public static final ImmutablePoint ZERO = new ImmutablePoint(0, 0);
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public void setX(int value) { x = value; }
    public int getY() { return y; }
    public void setY(int value) { y = value; }
    public static Point add(Point a, Point b) { return new Point(a.x + b.x, a.y + b.y); }
    public static Point sub(Point a, Point b) { return new Point(a.x - b.x, a.y - b.y); }
    public static Point mul(Point a, int b) { return new Point(a.x * b, a.y * b); }
}


