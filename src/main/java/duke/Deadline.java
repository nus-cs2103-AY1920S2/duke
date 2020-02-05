package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task the user would record.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a new Deadline object.
     *
     * @param name Name of Deadline Task.
     * @param by Deadline of the Task.
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Constructs a new Deadline object.
     *
     * @param name Name of Deadline Task.
     * @param by Deadline of the Task.
     * @param isDone Done status of the Task.
     */
    public Deadline(String name, LocalDate by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline of the Task.
     *
     * @return Deadline of the Task.
     */
    public String getBy() {
        return by.toString();
    }

    @Override
    public String getMnemonic() {
        return "D";
    }

    @Override
    public String toString() {
        return "[" + getMnemonic() + "]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyy")) + ")";
    }
}
