/**
 * Represents the exception that occurs when an invalid element in TaskList is accessed.
 */
class TaskListIndexOutOfBoundsException extends IndexOutOfBoundsException {
    TaskListIndexOutOfBoundsException(String message) {
        super(message);
    }
}