package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Dateline is a task with a specific date.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor that takes in description and date of task.
     *
     * @param description of the task
     * @param by when the task should be completed
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}