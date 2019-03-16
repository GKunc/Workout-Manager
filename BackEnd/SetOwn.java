package BackEnd;

import java.util.LinkedList;

public class SetOwn {
    private LinkedList<Interval> reps;

    public SetOwn() { reps = new LinkedList<>(); }
    public SetOwn(Interval interval) {
        reps.add(interval);
    }
    public SetOwn(LinkedList<Interval> _reps) { reps = _reps; }

    public LinkedList<Interval> getReps() { return reps; }

    public void setReps(LinkedList<Interval> _reps) { reps = _reps; }

    public void addReps(Interval interval) { reps.add(interval); }
    public void addReps(LinkedList<Interval> intervals) { reps.addAll(intervals); }

    public String toString() {
        String result = new String();
        int counter = 1;
        for(Interval i : reps) {
            result += "Repetition #" + counter + "\n" + i + "\n";
            ++counter;
        }
        return result;
    }
}
