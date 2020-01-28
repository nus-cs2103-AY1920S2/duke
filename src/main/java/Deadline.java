public class Deadline extends Task {
    private String deadlineDate;

    /**
     * Deadline constructor.
     *
     * @param deadlineArgs Joined string of the user input command arguements
     * @throws DukeException Incorrect format/missing date
     */
    public Deadline(String deadlineArgs) throws DukeException {
        super(deadlineArgs.split(" /by ")[0]);
        try {
            this.deadlineDate = deadlineArgs.split(" /by ")[1];
        } catch (Exception e) {
            throw new DukeException(3);
        }
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

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + deadlineDate;
    }
}