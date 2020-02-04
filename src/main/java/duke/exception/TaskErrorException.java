package duke.exception;

/**
 * Indicates that a task cannot be created
 */
public class TaskErrorException extends Exception {
    enum TaskErrorType {
        DEADLINE_BY_MISSING, EVENT_AT_MISSING, TASK_DESC_MISSING;
    }

    /**
     * Constructor with a message
     * @param msg
     */
    public TaskErrorException (String msg) {
        super(String.format("Error when creating task: %s is missing", msg));
    }
}
