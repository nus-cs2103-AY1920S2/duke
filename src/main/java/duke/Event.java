package duke;

import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;

public class Event extends Task {
    protected LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.typeD = "E";
        this.date = date;
    }

    public void setTime(String time) {
        this.timeD = time;
    }

    public String getTime() {
        return this.timeD;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getTimeStamp() {
        return "(at: " +  date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))  + " " +  this.timeD +")";
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s ",this.typeD,getStatusIcon(),this.description) + getTimeStamp()
                + " ";
    }
}