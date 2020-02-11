package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class extends from Task class and has an extra property.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * construct a Deadline instance by specify its due date.
     * @param description the description of the Deadline.
     * @param dueDate the due date of the Deadline.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = LocalDate.parse(dueDate);
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
