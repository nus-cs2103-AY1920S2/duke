import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This represents a Deadline task, which is a subset of class Task
 * A deadline has a date associated with it
 */

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a new deadline
     * @param description This is the description of the deadline
     * @param by This is the date that the deadline is due by
     */

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the date that the deadline is due by
     * @return The date that the deadline is due by
     */

    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

