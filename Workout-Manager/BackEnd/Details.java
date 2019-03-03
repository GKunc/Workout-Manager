package BackEnd;

import java.util.LinkedList;

public class Details {
    private LinkedList<Set> sets;

    public Details() { sets = new LinkedList<>(); }
    public Details(Set _sets) { sets = new LinkedList<>(); sets.add(_sets); }
    public Details(LinkedList<Set> _sets) { sets = _sets; }

    public LinkedList<Set> getSets() { return sets; }

    public void setSets(LinkedList<Set> _sets) { sets = _sets; }

    public void addSets(Set _sets) { sets.add(_sets); }
    public void addSets(LinkedList<Set> _sets) { sets.addAll(_sets); }

    @Override
    public String toString() {
        String result = "\n";
        int counter = 1;
        for(Set set : sets) {
            result += "Set #" + counter+ "\n" + set + "\n";
            counter++;
        }
        return result;
    }

   /* public static void main(String args[]) {
        Set set = new Set();

        Interval inter1 = new Interval(80.2,500,90);
        Interval inter2 = new Interval(79.8,500,90);
        set.addReps(inter1);
        set.addReps(inter2);


        LinkedList<Interval> intervals = new LinkedList<>();
        Interval interval1 = new Interval(15.2,100,60);
        Interval interval2 = new Interval(14.7,100,60);
        Interval interval3 = new Interval(13.5,100,60);
        intervals.add(interval1);
        intervals.add(interval2);
        intervals.add(interval3);

        Set set1 = new Set(intervals);

        Details detail_workout1 = new Details();
        System.out.println(detail_workout1);
        detail_workout1.addSets(set);
        System.out.println(detail_workout1);
        detail_workout1.addSets(set1);
        System.out.println(detail_workout1);

        System.out.println("------------------------------------");
        LinkedList<Set> sets1 = new LinkedList<>();
        sets1.add(set);
        sets1.add(set1);

        Details detail_workout2 = new Details(sets1);

        System.out.println(detail_workout2);

        System.out.println("------------------------------------");


    }*/
}
