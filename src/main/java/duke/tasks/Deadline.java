package duke.tasks;

/**
 * Deadline class for tasks that have a time limit to complete by.
 */
public class Deadline extends Task {

    /**
     * Provides the time deadline by which the Task has to be completed.
     */
    private final String limit;

    /**
     * Constructor for the Task type Deadline.
     *
     * @param command raw String data entered to create the Deadline Task.
     * @param limit time limit by which the Deadline Task has to be completed.
     */
    public Deadline(String command, String limit) {
        super(command);
        this.limit = limit;
    }

    /**
     * Gives a message indicating the Deadline Task has been added.
     *
     * @return String indicating the Deadline Task has been added.
     */
    @Override
    public String taskAddedMessage() {
        return "==> Added unique Deadline task: " + this;
    }

    /**
     * Provides a String representation of the Deadline Task object with tag [D] and its time limit.
     *
     * @return String representation of the Deadline Task with limit and tag.
     */
    @Override
    public String toString() {
        return "[D] " + this.command + " (by: " + this.limit + ")";
    }

}
