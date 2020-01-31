import java.time.LocalDate;

/**
 * A Deadline task.
 */
public class Deadline extends DateTimeTask {
    public Deadline(String description, LocalDate by) {
        super(description, by);
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getDateTime() + ")";
    }
}
