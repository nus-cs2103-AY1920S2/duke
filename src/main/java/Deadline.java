import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task with a description and a deadline.
 */
public class Deadline extends Task {

    /**
     * The deadline of the task.
     */
    Date deadline;

    /**
     * Constructor for Deadline that takes in a description of the task and
     * the deadline for the task.
     *
     * @param description the description of the task
     * @param deadline the deadline of the task
     */
    public Deadline(String description, String deadline) {
        super(description);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm");

        this.deadline = format.parse(deadline, new ParsePosition(0));
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("MMM d yyyy HH:mm");

        return "[D]" + super.toString() + " (by: " + format.format(deadline) + ")";
    }
}
