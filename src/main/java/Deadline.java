/**
 * Represents a Deadline which inherits from Task and is stored/managed by Duke
 */
public class Deadline extends Task {
    /**
     * Stores the time the deadline is supposed to be complete
     */
    protected String by;

    /**
     * Creates a deadline object with given description and time to complete (by)
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     *  Gives a string representation of the Deadline by building upon parent's representation method
     * @return a string representation
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
