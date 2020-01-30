import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A more detailed implementation of the Task class with specified deadline.
 */
public class Deadline extends Task {

    /**
     * The deadline.
     */
    protected LocalDate by;

    /**
     * Constructs a new instance of Deadline.
     *
     * @param description the description of the task.
     * @param by          the deadline of the task.
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
