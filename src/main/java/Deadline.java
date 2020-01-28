import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        return serialize("D", by.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Override
    public String toString() {
        String formatDate = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[D]%s (by: %s)", super.toString(), formatDate);
    }
}