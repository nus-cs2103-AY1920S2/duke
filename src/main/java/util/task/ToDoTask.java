package util.task;

/**
 * Represents a to do task.
 * A to do task has a description, and a true or false on whether it is completed.
 */
public class ToDoTask extends Task {
    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Sets this util.task.ToDoTask's complete status to true.
     * Overrides the abstract method in util.Task.
     *
     * @return A new complete util.task.ToDoTask with the same description, deadline as the util.task.ToDoTask instance.
     */
    @Override
    public ToDoTask completeTask() {
        return new ToDoTask(this.description, true);
    }

    @Override
    public String toString() {
        return "[T] [" + super.getStatusIcon() + "] "
                + this.description;
    }
}