package duke;

import duke.task.Task;

/**
 * Thrown to indicate that a task is a duplicate of another task in the task list.
 */
class DuplicateTaskException extends DukeException {
    /**
     * Constructs a {@code DuplicateTaskException} with the duplicated task.
     *
     * @param task the duplicated task
     */
    DuplicateTaskException(Task task) {
        super("Oops! " + task + " is already in the list.");
    }
}
