package sample.misc;

public class ImmutablePoint extends Point {
    public ImmutablePoint(int x, int y) {
        super(x, y);
    }
    @Override
    public void setX(int value) { }
    @Override
    public void setY(int value) { }
}
