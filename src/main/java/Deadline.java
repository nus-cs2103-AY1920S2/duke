import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This is a subclass of task which simulates tasks with deadline.
 */
public class Deadline extends Task {

    /** Deadline time of this task. */
    protected LocalDate by;

    /**
     * Class constructor inherits form Task constructor and add deadline time.
     *
     * @param description Description of this deadline task.
     * @param by Deadline time.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Override String representation of deadline tasks.
     *
     * @return Type D and its description and deadline time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getData() {
        return "D" + super.getData() + "|" + this.by;
    }
}
