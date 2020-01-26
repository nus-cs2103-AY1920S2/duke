public class Deadline extends Task {

    /**
     * Constructor for Deadline
     * @param description description of Deadline
     */
    public Deadline(String description, String due) {
        super(description);
        period = due;
        type = "deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + period + ")";
    }
}
