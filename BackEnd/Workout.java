//////////////////////////////////////////////////////////////////////////////////
//                                                                              //
// Basic class for workout, other classes will extend it for more informations  //
//                                                                              //
//////////////////////////////////////////////////////////////////////////////////

package BackEnd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Workout { // todo https://www.polar.com/accesslink-api/?shell#polar-accesslink-api
    private Date date;
    private double distance;
    private Duration duration; // hh:mm:ss
    private String feeling;
    private String description;
    private Details details; // todo add fields and methods to store series and reps


    public Workout() {
        date = new Date();
        duration = new Duration();
        description = new String();
    }
    public Workout(Date _date, double _distance, Duration _duration, String _feeling, String _description, Details _details) {
        date = _date;
        distance = _distance;
        duration = new Duration(_duration);
        feeling = _feeling;
        description = _description;
        details = _details;
    }

    public void setDate(Date _date) { date = _date; }
    public void setDistance(double _distance) { distance = _distance; }
    public void setDuration(Duration _duration) { duration = _duration; }
    public void setFeeling(String _feeling) { feeling = _feeling; }
    public void setDescription(String _description) { description = _description; }
    public void setDetails(Details _details) { details = _details; }

    public Date getDate() { return date; }
    public double getDistance() { return distance; }
    public Duration getDuration() { return duration; }
    public String getFeeling() { return feeling; }
    public String getDescription() { return description; }
    public Details getDetails() { return  details; }

    @Override
    public String toString() {
        return "Data: " + getDate() + "\nDystans: " + getDistance() + " km\nDlugosc trwania: " + getDuration() + "\nOdczucia: " + getFeeling()
                +"\nOpis: " + getDescription() + "\nSzczegoly treningu: " + getDetails()
                + "\n-----------------------------------------------\n";
    }

    public void addDescription(String _description) {
        description += _description;
    }

    public static void saveWorkout(String path, Workout workout) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        writer.write(workout.toString());
        writer.close();
    }


    /*public static void main(String args[]) throws IOException {
         String file_path = "./workout_file.txt";

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

        ////////////////////

        Workout workout1 = new Workout();
        saveWorkout(file_path, workout1);


        Duration duration2 = new Duration(01,10,22);
        Date data = new Date(2000, 11, 21); // find something different then Date!!
        Details details_workout2 = new Details();
        details_workout2.addSets(set);
        details_workout2.addSets(set1);

        Workout workout2 = new Workout(data, duration2, "ok", "Hard session but everything done", details_workout2);
        saveWorkout(file_path, workout2);


        Date data3 = new Date(2019, 01, 01);
        Duration duration3 = new Duration(1,3,5);
        Details details_workout3 = new Details(set);
        Workout workout3 = new Workout(data3, duration3, "bad", "Legs are very tired. Need some rest!", details_workout3);
        saveWorkout(file_path, workout3);

    } */
}
