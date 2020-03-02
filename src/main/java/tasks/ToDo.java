package tasks;

import tasks.Task;

/**
 * A minimalistic task that only contains a description.
 */
public class ToDo extends Task {

    /**
     * Constructor for a ToDo task.
     * @param description How the user describes the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a representation of task.
     * @return String that represents the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
