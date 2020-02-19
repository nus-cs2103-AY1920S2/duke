package duke.task;

import duke.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline type class extending from the
 * abstract <code>DatedTask</code> class, which has a name and a <code>LocalDateTime</code> for the due date and time.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class Deadline extends DatedTask {

    /**
     * Deadline constructor with LocalDateTime for date.
     *
     * @param name of the deadline task.
     * @param deadline of the task.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline);
    }

    /**
     * Deadline constructor with String for date.
     *
     * @param name of the deadline task.
     * @param deadline of the task.
     */
    public Deadline(String name, String deadline) {
        super(name, DateTimeUtil.stringAsDateTime(deadline));
    }

    /**
     * Gets the save-string representation of the task.
     *
     * @return the String representation of the task Storage can save.
     */
    @Override
    public String toSaveString() {
        //D0Finish project@June 6
        return "D" + (isDone ? "1" : "0") + name + "@" + getDate();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + getDate().format(DateTimeFormatter.ofPattern("MMM d yyyy h.mma")) + ")";
    }
}
