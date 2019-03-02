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

public class Workout { // change to abstract
    private Date date;
    private Duration duration; // hh:mm:ss
    private String feeling;
    private String description;

    public Workout() {
        date = new Date();
        duration = new Duration();
        description = new String();
    }
    public Workout(Date _date, Duration _duration, String _description) {
        date = _date;
        duration = new Duration(_duration);
        description = _description;
    }

    public void setDate(Date _date) { date = _date; }
    public void setDuration(Duration _duration) { duration = _duration; }
    public void setFeeling(String _feeling) { feeling = _feeling; }
    public void setDescription(String _description) { description = _description; }

    public Date getDate() { return date; }
    public Duration getDuration() { return duration; }
    public String getFeeling() { return feeling; }
    public String getDescription() { return description; }

    public String toString() {
        return "Data: " + getDate() + "\nDlugosc trwania: " + getDuration() + "\nOpis: " + getDescription();
    }

    public void addDescription(String _description) {
        description += _description;
    }

    public static void saveWorkout(String path, Workout workout) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));

        writer.close();
    }

    public static void main(String args[]) throws IOException {
        String file_path = "./workout_file.txt";

        Workout workout1 = new Workout();
        saveWorkout(file_path, workout1);

        Date data = new Date(2000, 11, 21); // find something different then Date!!
        Workout workout2 = new Workout(data, new Duration(01,10,22), "ok");
        saveWorkout(file_path, workout2);

        Date data1 = new Date(2019, 01, 01);
        Duration duration1 = new Duration(1,3,5);
        Workout workout3 = new Workout(data1, duration1, "bad");
        saveWorkout(file_path, workout3);

    }
}
