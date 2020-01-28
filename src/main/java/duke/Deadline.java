package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representation of a deadline with a time.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Deadline constructor.
     * @param description desc
     * @param a a
     */
    public Deadline(String description, String a) {
        super(description);
        this.by = LocalDate.parse(a);
    }

    /**
     * Overloaded deadline constructor.
     * @param description desc
     * @param a a
     * @param mark mark
     */
    public Deadline(String description, String a, boolean mark) {
        super(description, mark);
        this.by = LocalDate.parse(a);
    }

    /**
     * Custom toString implementation.
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Override saveFormat method to generate neatly formatted information for saving.
     * @return String
     */
    @Override
    public String saveFormat() {
        return "D" + " , " + (super.isDone ? "1" : "0") + " , " + super.description + " , " + this.by;
    }
}