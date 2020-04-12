import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent a Deadline.
 * Deadline is a Task and is described by its String <code>description</code> and
 * * a boolean <code>isDone</code> to indicate whether a Deadline is completed.
 * <code>by</code> describes the date at which the Deadline is due.
 */
public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
