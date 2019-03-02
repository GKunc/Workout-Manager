package BackEnd;

public class Duration {
    private int hours;
    private int minutes;
    private int seconds;

    public Duration() {
        hours = 0;
        minutes = 0;
        seconds = 0;
    }

    public Duration(int _hours, int _minutes, int _seconds) {
        hours = _hours;
        minutes = _minutes;
        seconds = _seconds;
    }

    public Duration(Duration _duration) {
        hours = _duration.getHours();
        minutes = _duration.getMinutes();
        seconds = _duration.getSeconds();
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public String toString() {
        String h, m, s;
        if(getHours() < 10)  h = "0" + getHours();
        else h = Integer.toString(getHours());

        if(getMinutes() < 10)  m = "0" + getMinutes();
        else m = Integer.toString(getMinutes());

        if(getSeconds() < 10)  s = "0" + getSeconds();
        else s = Integer.toString(getSeconds());

        return h + ":" + m + ":" + s;
    }

};
