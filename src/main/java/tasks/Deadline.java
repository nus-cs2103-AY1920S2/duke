package tasks;

import tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task user wants to complete by a certain time and date.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline task.
     * @param description How the user describes the task.
     * @param by Time by which user has to complete task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline task in a nice format suitable for printing.
     * @return String representing the details of the deadline.
     */
    @Override
    public String toString() {
        String formatDateTime = by.format(DateTimeFormatter.ofPattern("dd LLL yyyy HH:mma"));
        return "[D]" + super.toString() + " (by: " + formatDateTime + ")";
    }
}
