import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Event
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 11 February 2020
 *
 */

/**
 * Event class extends the Task class
 * @author Daniel Alfred Widjaja
 */
public class Event extends Task {

    protected String connector;
    protected LocalDate datetime;

    /**
     * Constructs a Event instance, given the description,
     * connector, datetime and isDone status.
     * @param description The Description of the Event.
     * @param connector The word connecting between description and datetime.
     * @param datetime The datetime of the Event.
     * @param isDone The done status of the Event.
     * @param priority The priority of the Event.
     */
    public Event(String description, String connector, String datetime, boolean isDone, int priority) {
        super(description, isDone, priority);
        this.connector = connector;
        this.datetime = LocalDate.parse(datetime);
    }

    public Event(String description, String connector, String datetime, int priority) {
        this(description, connector, datetime, false, priority);
    }

    public Event(String description, String connector, String datetime) {
        this(description, connector, datetime, false, 10);
    }

    /**
     * Custom string to be saved in database.
     * @return Database formatted string.
     */
    public String getFileString() {
        return "E|" + isDone + "|" + description + "|" + connector + "|" + datetime + "|" + priority;
    }

    /**
     * Overrides the default toString method make
     * a custom string.
     * @return Printed format for Task.
     */
    @Override
    public String toString() {
        return ("[E][" + getStatusIcon() + "] " + description + " (" + connector + ": " + this.getDatetime() + ")");
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
