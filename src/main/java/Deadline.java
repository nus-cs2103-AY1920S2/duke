public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for the Deadline object, a subclass of Task
     * @param description String containing the description of the task
     * @param by String setting the deadline for this task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    /**
     * Formats this object as a String to be printed out
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}