package duke.tasks;

import duke.enums.TType;

import java.time.LocalDate;

/**
 * Represents a task to be done.
 */
public class Todo extends Task {
    /**
     * @return type of task in TType format.
     */
    public TType getType() {
        return TType.TODO;
    }

    /**
     * @return null as todo tasks has no dates.
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * Constructs todo task with default false isDone.
     * @param task Description of task.
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Constructs a todo task with user defined isDone.
     * @param isDone Whether the task is done.
     * @param task Description of task.
     */
    public Todo(boolean isDone, String task) {
        super(isDone, task);
    }

    /**
     * @return String form of task, will show ✓ if done and ✗ if not.
     */
    @Override
    public String toString() {
        if (isDone) {
            return  "[T][✓] " + task;
        } else {
            return "[T][✗] " + task;
        }
    }
}
