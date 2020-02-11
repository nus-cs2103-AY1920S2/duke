import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a new Deadline object.
     * @param content Description of deadline.
     * @param by Date to complete deadline by.
     */
    public Deadline(String content, String by) {
        super(content);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string describing the deadline in the format for saving.
     * @return String describing the deadline.
     */
    @Override
    public String toStore() {
        return "[D]" + super.toStore() + " (at: "
                + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
    }

    /**
     * Returns a string describing the deadline in the format for printing.
     * @return String describing the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyy")) + ")";
    }
}
