package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class extends from Task class and has an extra property.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * construct a Deadline instance by specify its due date.
     * @param description the description of the Deadline.
     * @param by the due date of the Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
