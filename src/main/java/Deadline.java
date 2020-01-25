import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent tasks that need to be done before a specific date/time
 * e.g., submit report by 11/10/2019 5pm
 */
public class Deadline implements Task {
    protected String description;
    protected LocalDate deadline;
    protected boolean isDone;

    public Deadline(String description, String deadline) {
        this(description, deadline, false);
    }

    public Deadline(String description, String deadline, boolean isDone) throws DateTimeException {
        this.description = description;
        this.deadline = LocalDate.parse(deadline);
        this.isDone = isDone;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Returns a String (Unicode Character) based on Task completion status.
     * @return String representing Unicode character for check mark or cross
     */
    @Override
    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    /**
     * Mark task as done.
     */
    @Override
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark task as incomplete.
     */
    @Override
    public void markAsIncomplete() {
        this.isDone = false;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        // Deadline in yyyy-mm-dd format (e.g. 2020-10-15)
        // Output in MMM d yyyy e.g. (Oct 15 2020)
        // e.g. format: [D][✗] return book (by: Oct 15 2020)
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(),
                description,
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
