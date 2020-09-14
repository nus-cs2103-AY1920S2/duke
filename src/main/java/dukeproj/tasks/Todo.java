package dukeproj.tasks;

import dukeproj.enums.TType;

import java.time.LocalDate;

/**
 * Represents a task to be done.
 */
public class Todo extends Task {
    /**
     * Returns type of task.
     *
     * @return TType.TODO.
     */
    public TType getType() {
        return TType.TODO;
    }

    /**
     * Returns null as todo does not have a date.
     *
     * @return null as todo tasks has no dates.
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * Constructs todo task with default false isDone.
     *
     * @param task Description of task.
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Constructs a todo task with user defined isDone.
     *
     * @param isDone Whether the task is done.
     * @param task Description of task.
     */
    public Todo(boolean isDone, String task) {
        super(isDone, task);
    }

    /**
     * Returns the string form of todo.
     *
     * @return String form of todo, will show ✓ if done and ✗ if not.
     */
    @Override
    public String toString() {
        if (isDone) {
            return  "[T][Y] " + task;
        } else {
            return "[T][N] " + task;
        }
    }
}
