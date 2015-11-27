package dataContainers;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public class Point {

    private int _x;
    private int _y;
    private int _tolerance;

    private float _xMinLimit;
    private float _xMaxLimit;
    private float _yMinLimit;
    private float _yMaxLimit;

    public Point (int x, int y, int tolerance) {
        _x = x;
        _y = y;
        _tolerance = tolerance;

        _xMinLimit = _x * (100 - _tolerance) / 100;
        _xMaxLimit = _x * (100 + _tolerance) / 100;
        _yMinLimit = _y * (100 - _tolerance) / 100;
        _yMaxLimit = _y * (100 + _tolerance) / 100;
    }

    public boolean checkIfTolerated(int x, int y) {
        if (x >= _xMinLimit && x <= _xMaxLimit) {
            if (y >= _yMinLimit && y <= _yMaxLimit) {
                return true;
            }
        }
        return false;
    }
}
