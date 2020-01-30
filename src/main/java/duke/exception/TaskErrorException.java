package duke.exception;

/**
 * Indicates that a task cannot be created
 */
public class TaskErrorException extends Exception {
    enum TaskErrorType {
        DeadlineByMissing, EventAtMissing, TaskDescMissing;
    }

    /**
     * Constructor with a message
     * @param msg
     */
    public TaskErrorException (String msg) {
        super(String.format("Error when creating task: %s is missing", msg));
    }
}
