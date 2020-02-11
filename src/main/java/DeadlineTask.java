import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    LocalDate by;

    public DeadlineTask(String desc, LocalDate by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override 
    public int compareTo(Task other) {
        return this.by.compareTo(((DeadlineTask) other).by);
    }
}