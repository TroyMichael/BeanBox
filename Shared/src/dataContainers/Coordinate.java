package dataContainers;

/**
 * Created by Michael on 09.11.2015.
 *
 */
public class Coordinate {
    private int _x;
    private int _y;

    public Coordinate(int x, int y) {
        _x = x;
        _y = y;
    }

    public int getX() {
        return _x;
    }

    public void setX(int x) {
        _x = x;
    }

    public int getY() {
        return _y;
    }

    public void setY(int y) {
        _y = y;
    }
}
