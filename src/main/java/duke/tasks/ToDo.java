package duke.tasks;

/**
 * Standard task to be done without time limit or specific time of happening.
 */
public class ToDo extends Task {

    /**
     * Constructor for this particular type of Task.
     *
     * @param command full raw text entered to create this particular Task.
     */
    public ToDo(String command) {
        super(command);
    }

    /**
     * Gives a message indicating that the To-Do Task has been added.
     *
     * @return String indicating that the To-Do Task has been added.
     */
    @Override
    public String taskAddedMessage() {
        return "==> Added unique ToDo task: " + this;
    }

    /**
     * Provides a String representation of the Task object with tag [T].
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return "[T] " + this.command;
    }

}
