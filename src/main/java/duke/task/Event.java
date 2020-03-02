package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event inherits from Task.
 * Has additional attribute for date storage.
 * @author Lua Jia Zheng.
 */
public class Event extends Task {
    private String date;
    private LocalDate date1;
    
    /**
     * SubClass of Task that tracks date/time.
     * @param title Title of the event
     * @param index Index of the event.
     * @param date Date of the event scheduled.
     */
    public Event(String title,int index,String date) {
        super(title,index);
        this.symbol = "E";
        this.date = date;
        try {
            this.date1 = LocalDate.parse(date);
        } catch (DateTimeException ex) {
            this.date1 = null;
        }
    }

    /**
     * Second constructor that does not preset "Done".
     * User provide if task is done.
     * @param title title of the event.
     * @param index index of the event in task tracker.
     * @param don boolean tracking if the task is done.
     * @param date date and time in the form of strings.
     */
    public Event(String title,int index, boolean don,String date) {
        super(title,index, don);
        this.symbol = "E";
        this.date = date;
        try {
            this.date1 = LocalDate.parse(date);
        } catch (DateTimeException ex) {
            this.date1 = null;
        }
    }

    /**
     * Get the a String output to be stored in a txt file.
     * @return returns formatted String to be stored in a txt file.
     */
    public String getInfo() {
        int binary = 0;
        if (this.done) {
            binary = 1;
        }
        return String.format("%s|%d|%s|%s", this.symbol, binary, this.title,this.date);
    }

    @Override
    public String toString() {
        String crosstick = "";
        if (this.done) {
            crosstick = "✔";
        } else {
            crosstick = "✘";
        }
        if (this.date1 != null) {
            String dateFormatted = this.date1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return String.format("[%s][%s] %s (at: %s)", this.symbol, crosstick, this.title, dateFormatted);
        } else {
            return String.format("[%s][%s] %s (at: %s)", this.symbol, crosstick, this.title, date);
        }
    }
}
