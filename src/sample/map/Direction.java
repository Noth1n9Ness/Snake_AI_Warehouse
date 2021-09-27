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
        UP.directions = new Direction[]{LEFT, UP, RIGHT};
        DOWN.directions = new Direction[]{RIGHT, DOWN, LEFT};
        RIGHT.directions = new Direction[]{UP, RIGHT, DOWN};
        LEFT.directions = new Direction[]{DOWN, LEFT, UP};
    }

    @Override
    public Direction getDirection() {
        return this;
    }
}
