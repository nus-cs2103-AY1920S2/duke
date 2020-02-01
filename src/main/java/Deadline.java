import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline with a description String, an isDone boolean, and a deadline
 */
public class Deadline extends Task{
    protected LocalDate by;

    /**
     * Creates a Deadline object, an extension of the Task object with a deadline date
     * @param description a description of the Deadline
     * @param by a date of the Deadline
     */
    public Deadline(String description, LocalDate by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String byString = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }
}
