package duke.task;

import duke.task.Task;

/**
 * The To-do class represents a task without any date/time attached to it e.g., visit new theme park.
 */
public class Todo extends Task {
    /**
     * Create a to-do task from description provided.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
