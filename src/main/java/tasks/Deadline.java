package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class is a type of task.
 */
public class Deadline extends Task{
    /**
     * The constructor for the Deadline class.
     * @param description The description of the deadline.
     * @param date The date for the deadline.
     */
    public Deadline(String description, LocalDate date) {
        super(description, date);
    }

    /**
     * Gets the type of the task.
     * @return The type of the task.
     */
    public String getType() {
        return "[D]";
    }

    public String toString() {
        return getType() + getStatusIcon() + " " + description
                + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Generates a string when saving to the hard disk.
     * @return The string to be written to duke.txt.
     */
    public String saveString() {
        return "D|" + (isDone? "1|" : "0|") + description + "|" + date;
    }
}
