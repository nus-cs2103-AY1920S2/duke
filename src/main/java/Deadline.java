import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Deadline
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 11 February 2020
 *
 */

/**
 * Deadline class extends the Task class
 * @author Daniel Alfred Widjaja
 */
public class Deadline extends Task {

    protected String connector;
    protected LocalDate datetime;

    /**
     * Constructs a Deadline instance, given the description,
     * connector, datetime and isDone status.
     * @param description The Description of the Deadline.
     * @param connector The word connecting between description and datetime.
     * @param datetime The datetime of the Deadline.
     * @param isDone The done status of the Deadline.
     * @param priority The priority of the Deadline.
     */
    public Deadline(String description, String connector, String datetime, boolean isDone, int priority) {
        super(description, isDone, priority);
        this.connector = connector;
        this.datetime = LocalDate.parse(datetime);
    }

    public Deadline(String description, String connector, String datetime, int priority) {
       this(description, connector, datetime, false, priority);
    }

    public Deadline(String description, String connector, String datetime) {
        this(description, connector, datetime, false, 10);
    }

    /**
     * Custom string to be saved in database.
     * @return Database formatted string.
     */
    public String getFileString() {
        return "D|" + isDone + "|" + description + "|" + connector + "|" + datetime + "|" + priority;
    }

    /**
     * Overrides the default toString method make
     * a custom string.
     * @return Printed format for Task.
     */
    @Override
    public String toString() {
        return ("[D][" + getStatusIcon() + "] " + description + " (" + connector + ": " + this.getDatetime() + ")");
    }

    /**
     * Returns the datetime of the deadline.
     * The return format would be:
     * MMM d yyyy
     * ex:
     * FEB 11 2020
     * @return datetime eof the deadline.
     */
    public String getDatetime() {
        return this.datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}
