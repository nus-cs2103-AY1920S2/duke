package duke.tasks;

import duke.tasks.Task;

/**
 * Todo class represents a todo task.
 */
public class Todo extends Task {

    /**
     * Creates an incomplete todo task with given description.
     *
     * @param description description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a todo task.
     *
     * @param description description of task.
     * @param done boolean indicating if task is completed.
     */
    public Todo(String description, int done) {
        super(description, done);
    }

    /**
     * Formats to do task for save to database.
     *
     * @return formatted string that represents task.
     */
    public String toPrint() {
        if (this.isDone) {
            return "T | " + 1 + " | " + this.description;
        } else {
            return "T | " + 0 + " | " + this.description;
        }
    }

    /**
     * Formats to do task for printing.
     *
     * @return formatted string that represents task.
     */
    public String toString() {
        if (this.isDone) {
            return "[T][✓] " + this.description;
        } else {
            return "[T][✗] " + this.description;
        }
    }
}