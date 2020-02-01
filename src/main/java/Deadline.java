/**
 * The Deadline class is a type of Task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Creates a Deadline Task object.
     *
     * @param description A string representation of the task description.
     * @param by A string representation of the deadline for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the task description, deadline and status whether is it done.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}