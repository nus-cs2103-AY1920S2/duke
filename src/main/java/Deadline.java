/**
 * Represents a Task which has a deadline.
 */
public class Deadline extends Task {

    protected TaskDate td;

    /**
     * Constructor for a Deadline object
     * @param desc Task description
     * @param td TaskDate deadline
     */
    public Deadline(String desc, TaskDate td) {
        super(desc);
        this.td = td;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + td + ")";
    }
}