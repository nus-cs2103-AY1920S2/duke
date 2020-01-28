import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representation of Deadline
 */
public class Deadline extends Task{
    protected LocalDateTime dL;

    /**
     * Deadline Constructor
     * @param description
     * @param dL
     */
    public Deadline(String description, LocalDateTime dL) {
        super(description);
        this.dL = dL;
    }

    /**
     * Deadline's overriden toString method
     * @return String representation of Deadline
     */
    public String toString() {
        String dateTimeFormat = dL.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
        return ("[D] [" + this.getStatusIcon() + "] " + this.description + " (by: " + dateTimeFormat + ")");
    }
}
