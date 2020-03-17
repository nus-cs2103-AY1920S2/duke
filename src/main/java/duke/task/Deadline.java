package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * duke.task.Deadline class which is a duke.task.Task but more specific
 */
public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}