package duke.exception;

public class TaskErrorException extends Exception {
    enum TaskErrorType {
        DeadlineByMissing, EventAtMissing, TaskDescMissing;
    }
    public TaskErrorException (String msg) {
        super(String.format("Error when creating task: %s is missing", msg));
    }
}
