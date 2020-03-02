package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline inherits from Task.
 * Has additional attribute for date storage.
 * @author Lua Jia Zheng.
 */
public class Deadlines extends Task {
    private String date;
    private LocalDate date1;

    /**
     * Deadline that extends task and tracks date/time.
     * @param title title of the event.
     * @param index index of the event in the task tracker.
     * @param symbol duke.task.Symbol D to signify Deadline events
     * @param date date and time in the form of strings
     */

    public Deadlines(String title, int index, Symbol symbol, String date) {
        super(title,index);
        this.symbol = "D";
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
    public Deadlines(String title,int index, boolean don,String date) {
        super(title,index, don);
        this.symbol = "D";
        this.date = date;
        try {
            this.date1 = LocalDate.parse(date);
        } catch (DateTimeException ex) {
            this.date1 = null;
        }
    }

    /**
     * Get the a String output to be stored in a txt file.
     * @return formated String to be stored in a txt file.
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
        if (date1 != null) {
            String dateFormatted = this.date1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return String.format("[%s][%s] %s (by: %s)", this.symbol, crosstick, this.title, dateFormatted);
        } else {
            return String.format("[%s][%s] %s (by: %s)", this.symbol, crosstick, this.title, date);
        }
    }
}
