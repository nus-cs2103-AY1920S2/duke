package duke.task;

/**
 * Todo is a subtype of Task.
 * Todo is meant for when a task is needed to be done without a deadline.
 * Eg. Read book.
 *
 * @author Dargo
 */
public class Todo extends Task {

    /**
     * Event subtype of Task.
     *
     * @param type Type of task.
     * @param task Input command for the task.
     */
    public Todo(String type, String task) {
        super(type, task);
    }

    /**
     * Returns the formatted string.
     *
     * @return Formatted string of task object in question.
     */
    @Override
    public String toString() {

        return super.toString();
    }
}

