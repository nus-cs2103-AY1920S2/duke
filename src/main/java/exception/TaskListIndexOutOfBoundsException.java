package exception;

/**
 * Represents the exception that occurs when an invalid element in TaskList is accessed.
 */
public class TaskListIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public TaskListIndexOutOfBoundsException(String message) {
        super(message);
    }
}