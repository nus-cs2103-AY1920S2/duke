package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates Event Task.
 */
public class Event extends Task {
    protected LocalDate date;

    /**
     * Initialise description, time and date.
     * @param description of task
     * @param date of task
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.typeD = "E";
        this.date = date;
    }

    public void setRawDate(String date) {
        this.rawDate = date;
    }

    /**
     * Sets time for Event Task.
     * @param time for Event Task
     */
    public void setTime(String time) {
        this.timeD = time;
        this.rawTime = time;
    }

    /**
     * Retrieves time.
     * @return time from Event Task
     */
    public String getTime() {
        return this.timeD;
    }

    /**
     * Retrieves date.
     * @return date from Event Task
     */
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Retrieves formatted time.
     * @return formatted time from Event Task
     */
    @Override
    public String getTimeStamp() {
        return "(at: " +  date.format(DateTimeFormatter.ofPattern("MMM d yy"))  + " " +  this.timeD + ")";
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s ",this.typeD,getStatusIcon(),this.description) + getTimeStamp()
                + " ";
    }
}