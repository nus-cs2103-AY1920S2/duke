package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Dateline class that inherits from Task class.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline.
     * @param description Takes in a description
     * @param by Takes in local date time
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * To string method for Deadline.
     * @return string for toString method
     */
    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] "  + super.toString() + " (by: " + by.format(
                DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm")) + ")";
    }
}
