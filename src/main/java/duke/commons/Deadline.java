package duke.commons;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of task that could be added. A <code>Deadline</code> object corresponds to
 * a task with a specific date and time to be completed by.
 */

public class Deadline extends Task {

    protected String byString;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor for <code>Deadline</code>.
     * @param type <code>String</code> representing the type of the <code>Task</code> ("deadline").
     * @param isDone true if the <code>Task</code> is completed, false otherwise.
     * @param description <code>String</code> representing the description of the <code>Task</code>.
     * @param byString <code>String</code> representing the deadline of the <code>Task</code>.
     */
    public Deadline(String type, boolean isDone, String description, String byString) {
        super(type, isDone, description);
        this.byString = byString;

        //Date input format: yyyy-mm-dd HHmm
        String[] by = byString.split(" ");
        this.date = LocalDate.parse(by[0]);
        if (by.length > 1) {
            this.time = LocalTime.parse(by[1], DateTimeFormatter.ofPattern("HHmm"));
        } else {
            this.time = null;
        }
    }

    /**
     * Returns a <code>String</code> object representing the type of this <code>Task</code>.
     *
     * @return the <code>String</code> "D".
     */
    public String getTypeSymbol() {
        return "D";
    }

    /**
     * Returns an array of <code>String</code> objects representing this <code>Deadline</code>.
     *
     * @return a string array representation of the <code>Deadline</code> object.
     */
    @Override
    public String[] toDataTokens() {
        String isDoneString = String.valueOf(super.isDone);
        return new String[] {getTypeSymbol(), isDoneString, super.description, this.byString};
    }

    /**
     * Returns a <code>String</code> object representing this <code>Deadline</code>.
     *
     * @return a string representation of the <code>Deadline</code> object.
     */
    @Override
    public String toString() {
        String dateTime = "";
        dateTime += this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (this.time != null) {
            dateTime += " " + this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return "[" + getTypeSymbol() + "]" + super.toString() + " (by: " + dateTime + ")";
    }
}