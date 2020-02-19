package duke;

import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.DateFormat;


public class Deadline extends Task {
    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.typeD = "E";
        this.date = date;
    }

    public void setTime(String time) {
        String newtime = time.substring(0,2) + ":" + time.substring(2);
        DateFormat df = new SimpleDateFormat("HH:mm");
        DateFormat outputdf = new SimpleDateFormat("hh:mm aa");
        Date date = null;
        String output = null;
        try {
            date = df.parse(newtime);
            output = outputdf.format(date);
            this.timeD = output;
            //Date newtime2 = new SimpleDateFormat("hh:mm a").format(newtime);
            //this.timeD = new SimpleDateFormat("h:mm: a").format(newtime2)
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getTime() {
        return this.timeD;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getTimeStamp() {
        return "(by: " +  date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))  + " " +  this.timeD + ")";
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s ",this.typeD,getStatusIcon(),this.description) + getTimeStamp();
    }
}