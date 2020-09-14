simport java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline object characterized by an instrution and a date and time.
 */
public class Deadline extends Task{
    private LocalDateTime time;

    /**
     * Creates a new Deadline with the given instruction and time.
     * @param instruction Task content.
     * @param time Time when the task starts.
     */
    public Deadline (String instruction, LocalDateTime time) {
        super(instruction);
        this.time = time;
    }

    /**
     * Gets the time of a deadline.
     * @return the time of this deadline.
     */
    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Gets a string representation of a deadline.
     * @return the string representation of this deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
