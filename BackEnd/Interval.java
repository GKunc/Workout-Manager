package BackEnd;

public class Interval {
    private double time;
    private double distance;
    private double rest;

    public Interval() {
        time = 0;
        distance = 0;
        rest = 0;
    }
    public Interval(double _time, double _distance, double _rest) {
        time = _time;
        distance = _distance;
        rest = _rest;
    }

    public double getTime() { return time; }
    public double getDistance() { return distance; }
    public double getRest() { return rest; }

    public void setTime(double _time) { time = _time; }
    public void setDistance(double _distance) { distance = _distance; }
    public void setRest(double _rest) { rest = _rest; }

    @Override
    public String toString() {
        return "Time: " + time + " s\nDistance: " + distance + " m\nRest: " + rest + " s\n";
    }
}
