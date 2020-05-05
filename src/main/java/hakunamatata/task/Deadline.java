package hakunamatata.task;

import java.time.LocalDateTime;

import hakunamatata.Parser;

/**
 * Represents a deadline task.
 *
 * @author Wang Yuting
 */
public class Deadline extends Task {

    /**
     * This is the date and time of the deadline.
     */
    protected LocalDateTime by;

    /**
     * Creates a new <code>Deadline</code> with the given <code>description</code> and <code>by</code> (date and time).
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Creates a new <code>Deadline</code> with the given <code>description</code>, <code>by</code> (date and time) and
     * <code>isDone</code>.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, TaskType.DEADLINE);
        this.isDone = isDone;
        this.by = by;
    }

    /**
     * Gets the <code>by</code> (date and time) of the <code>Deadline</code>.
     *
     * @return The <code>by</code> (date and time) of the <code>Deadline</code>.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Converts the <code>Deadline</code> detail into a String.
     *
     * @return The detail of <code>Deadline</code> in String.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + Parser.getDateTimeString(this.by) + ")";
    }
}
