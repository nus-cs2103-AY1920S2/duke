import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;

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
    public Deadline(String description, String deadline) throws InvalidPropertiesFormatException {
        super(description);
        type = "Deadline";
        isSnoozeable = true;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.deadline = format.parse(deadline, new ParsePosition(0));

        if (this.deadline == null) {
            // invalid format
            throw new InvalidPropertiesFormatException("Wrong date format");
        }
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Task snooze(String time) throws InvalidPropertiesFormatException {
        return new Deadline(description, time);
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("MMM d yyyy HH:mm");

        return "[D]" + super.toString() + " (by: " + format.format(deadline) + ")";
    }
}
