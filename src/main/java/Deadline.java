public class Deadline extends Task {
    private String deadlineDate;

    /**
     * Deadline constructor.
     *
     * @param deadlineArgs Joined string of the user input command arguements
     */
    public Deadline(String deadlineArgs) {
        super(deadlineArgs.split(" /by ")[0]);
        this.deadlineDate = deadlineArgs.split(" /by ")[1];
    }

    /**
     * A method to return the details of the current Task object.
     * Contains the task done status and the task name.
     *
     * @return A String representation of the task details.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + this.deadlineDate + ")";
    }
}