package duke.commons;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of task that could be added. A <code>Event</code> object corresponds to
 * a task that occurs at a specific date and time.
 */

public class Event extends Task {

    protected String type;
    protected String atString;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor for <code>Event</code>.
     * @param type <code>String</code> representing the type of the <code>Task</code> ("event").
     * @param isDone true if the <code>Task</code> is completed, false otherwise.
     * @param description <code>String</code> representing the description of the <code>Task</code>.
     * @param atString <code>String</code> representing the time of the <code>Task</code>.
     */
    public Event(String type, boolean isDone, String description, String atString) {
        super(type, isDone, description);
        this.atString = atString;

        //Date input format: yyyy-mm-dd HHmm
        String[] at = atString.split(" ");
        this.date = LocalDate.parse(at[0]);
        if (at.length > 1) {
            this.time = LocalTime.parse(at[1], DateTimeFormatter.ofPattern("HHmm"));
        } else {
            this.time = null;
        }
    }

    /**
     * Returns a <code>String</code> object representing the type of this <code>Task</code>.
     *
     * @return the <code>String</code> "E".
     */
    public String getTypeSymbol() {
        return "E";
    }

    /**
     * Returns an array of <code>String</code> objects representing this <code>Event</code>.
     *
     * @return a string array representation of the <code>Event</code> object.
     */
    @Override
    public String[] toDataTokens() {
        String isDoneString = String.valueOf(super.isDone);
        return new String[] {getTypeSymbol(), isDoneString, super.description, this.atString};
    }

    /**
     * Returns a <code>String</code> object representing this <code>Event</code>.
     *
     * @return a string representation of the <code>Event</code> object.
     */
    @Override
    public String toString() {
        String dateTime = "";
        dateTime += this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (this.time != null) {
            dateTime += " " + this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return "[" + getTypeSymbol() + "]" + super.toString() + " (at: " + dateTime + ")";
    }
}