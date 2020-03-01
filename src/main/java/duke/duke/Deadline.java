package duke;

import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.DateFormat;

/**
 * Creates Deadline task.
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Initialise type and date and description.
     * @param description of task
     * @param date of task
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.typeD = "D";
        this.date = date;
    }

    /**
     * Formats time to 12 hour mode for Deadline Task.
     * @param time for Deadline Task
     */
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

    /**
     * Retrieves time.
     * @return time from Deadline Task
     */
    public String getTime() {
        return this.timeD;
    }

    /**
     * Retrieves date.
     * @return date from Deadline Task
     */
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Retrieves formatted time.
     * @return formatted time from Deadline Task
     */
    public String getTimeStamp() {
        return "(by: " +  date.format(DateTimeFormatter.ofPattern("MMM d yy"))  + " " +  this.timeD + ")";
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s ",this.typeD,getStatusIcon(),this.description) + getTimeStamp();
    }
}