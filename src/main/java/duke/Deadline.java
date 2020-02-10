package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * One of the tasks that could be created by user.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Deadline constructor.
     *
     * @param description Describes the task.
     * @param by When the task must be done.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getDate() {
        return by;
    }

    /**
     * Overwritten toString to fit format requirements.
     *
     * @return Formatted string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}