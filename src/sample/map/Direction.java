package sample.map;

import sample.misc.ImmutablePoint;
import sample.misc.Point;

public class Direction extends ImmutablePoint implements IDirection {
    private Direction(int x, int y) { super(x, y); }
    private Direction[] directions;

    public Direction[] getDirections() {
        return directions;
    }

    public static Direction UP = new Direction(0, -1);
    public static Direction DOWN = new Direction(0, 1);
    public static Direction LEFT = new Direction(-1, 0);
    public static Direction RIGHT = new Direction(1, 0);
    static {
        UP.directions = new Direction[]{UP, LEFT, RIGHT};
        DOWN.directions = new Direction[]{DOWN, LEFT, RIGHT};
        RIGHT.directions = new Direction[]{UP, DOWN, RIGHT};
        LEFT.directions = new Direction[]{UP, DOWN, LEFT};
    }

    @Override
    public Direction getDirection() {
        return this;
    }
}
