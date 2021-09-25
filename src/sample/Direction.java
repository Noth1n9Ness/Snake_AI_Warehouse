package sample;

public enum Direction {
    UP,
    LEFT,
    DOWN,
    RIGHT;

    public static boolean checkInvalidMove(Direction currentDirection, Direction nextDirection){
        if ((currentDirection == UP && nextDirection == DOWN) || (currentDirection == DOWN && nextDirection == UP)) {
            return false;
        }
        if ((currentDirection == RIGHT && nextDirection == LEFT) || (currentDirection == LEFT && nextDirection == RIGHT)) {
            return false;
        }
        return true;
    }
}
