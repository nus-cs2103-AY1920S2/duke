package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline is a special kind of task that needs to be done before a specific time.
 */
public class Deadline extends Task {

    protected String by = "";
    protected LocalDate todoDate;

    /**
     * Constructor of a deadline.
     * @param description A string with the details of the deadline.
     * @param by A string that represents a time.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor overloading, with a date as time.
     * @param description A string with the details of the deadline.
     * @param todoDate The best before date.
     */
    public Deadline(String description, LocalDate todoDate) {
        super(description);
        this.todoDate = todoDate;
    }

    /**
     * A getter method to access the time of this deadline.
     * @return A string that represents a time.
     */
    public String getDeadline() {
        if (!this.by.equals("")) {
            return this.by;
        }
        return this.todoDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Gives a string representation of the deadline, with the details and the time best before.
     * @return A string representation of the deadline.
     */
    @Override
    public String toString() {
        if (!this.by.equals("")) {
            return "[D]" + getDescription() + " (by: " + this.by + ")";
        } else {
            String dateToPrint = this.todoDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[D]" + getDescription() + " (by: " + dateToPrint + ")";
        }
    }
}