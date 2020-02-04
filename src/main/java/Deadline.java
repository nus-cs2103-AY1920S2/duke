import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents a Deadline which inherits from Task and is stored/managed by Duke
 */
public class Deadline extends Task {
    /**
     * Stores the time the deadline is supposed to be complete
     */
    protected String by;
    protected Optional<LocalDate> dueDate;

    /**
     * Creates a deadline object with given description and time to complete (by)
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        if (this.by.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.dueDate = Optional.of(LocalDate.parse(this.by));
        }
    }

    /**
     *  Gives a string representation of the Deadline by building upon parent's representation method
     * @return a string representation
     */
    @Override
    public String toString() {
        if (this.dueDate.isPresent()) {
            return "[D]" + super.toString() +  "(by: " +
                    this.dueDate.get().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + "(by: " + by + ")";
        }
    }
}
