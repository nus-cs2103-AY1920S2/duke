package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Deadline extends Task {
    /** The cut-off date for this deadline. */
    protected final LocalDate by;

    /**
     * Constructs a new deadline with a cut-off date.
     *
     * @param description the details of the deadline
     * @param by the cut-off date as text.
     */
    public Deadline(String description, LocalDate by) {
        this(description, false, by);
    }

    private Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public Deadline markDone() {
        return new Deadline(description, true, by);
    }

    @Override
    public String serialize() {
        // Date format is yyyy-mm-dd
        return serialize("D", by.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Override
    public String toString() {
        // Date format is MMM d yyyy
        String formatDate = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[D]%s (by: %s)", super.toString(), formatDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Deadline) {
            Deadline deadline = (Deadline)obj;
            return this.by.equals(deadline.by);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), by);
    }
}