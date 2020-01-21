public class Deadline extends Task{
    protected String due;

    /**
     * Constructor for Deadline
     * @param description description of Deadline
     */
    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due + ")";
    }
}
