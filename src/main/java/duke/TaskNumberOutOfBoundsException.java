package duke;

/**
 * Thrown when a task number is out of range.
 */
class TaskNumberOutOfBoundsException extends DukeException {
    /**
     * Constructs a {@code TaskNumberOutOfBoundsException} with the specified task number that is out of range.
     *
     * @param taskNumber the task number that is out of range
     */
    TaskNumberOutOfBoundsException(int taskNumber) {
        super("Oops! " + taskNumber + " doesn't correspond to any task.");
    }
}
