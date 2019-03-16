package BackEnd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ParseFile {
    private ArrayList<Workout> workouts;

    ParseFile(String path) throws IOException, ParseException {
        ArrayList<String> data = splitFile(path); // String for every workout
        workouts = new ArrayList<>();
        for(String trainingData : data) {
            Workout item = new Workout();
            List<String> workout = Arrays.asList(trainingData.split(",")); // split each training for seperate infos
            for(String details : workout) {

                if(details.contains("start-time")) {
                    String[] date = details.split(":");
                    int index = date[1].indexOf("T");
                    String strDate = "", strTime = "";
                    if (index != -1) {
                        strDate = date[1].substring(2, index);  // delete space and " char
                        strTime = date[1].substring(index + 1) + ":" + date[2] + ":" + date[3].substring(0,2);
                    }
                    SimpleDateFormat format = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
                    String dateToFormat = strDate + " " + strTime;
                    Date dateTime = new Date();
                    dateTime = format.parse( dateToFormat );
                    item.setDate(dateTime);
                }
                else if(details.contains("distance")) {
                    String[] distance = details.split(":");
                    item.setDistance(Double.parseDouble(distance[1])/1000);
                }
                else if(details.contains("duration")) {
                    String[] duration = details.split(":");
                    duration[1] = duration[1].substring(4); // delete space " and PT
                   // System.out.println(duration[1]);
                    int h, m, s;
                    int hours = 0 , minutes = 0, seconds = 0;
                    h = duration[1].indexOf("H");
                    m = duration[1].indexOf("M");
                    s = duration[1].indexOf(".");
                    if(h != -1) {
                       hours = Integer.parseInt(duration[1].substring(0,h));
                    }
                    if(m != -1 && h != -1) {
                        minutes = Integer.parseInt(duration[1].substring(h+1,m));
                    } else if (m != -1 && h == -1) {
                        minutes = Integer.parseInt(duration[1].substring(0,m));
                    }
                    if(s != -1 && m != -1) {
                        seconds = Integer.parseInt(duration[1].substring(m+1,s));
                    } else if (s != -1 && m == -1){
                        seconds = Integer.parseInt(duration[1].substring(0,s));
                    }
                        item.setDuration(new Duration(hours,minutes,seconds));
                }
            }
            workouts.add(item);
        }
    }

    public ArrayList<Workout> getWorkouts() { return workouts; }

    public ArrayList<String> splitFile(String path) throws IOException {
        ArrayList<String> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        while (line != null) {
            data.add(line);
            line = reader.readLine();
        }
        reader.close();

        return data;
    }



    public static void main(String args[]) {
        try {
            ParseFile file = new ParseFile("./data.txt");
            ArrayList<Workout> trainings = file.getWorkouts();
            for( Workout w : trainings)
                System.out.println(w);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
