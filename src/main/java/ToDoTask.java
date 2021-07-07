/**
 * Represents a to do task.
 * A to do task has a description, and a true or false on whether it is completed.
 */
class ToDoTask extends Task {
    ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Sets this ToDoTask's complete status to true.
     * Overrides the abstract method in Task.
     *
     * @return A new complete ToDoTask with the same description, deadline as the ToDoTask instance.
     */
    @Override
    ToDoTask completeTask() {
        return new ToDoTask(this.description, true);
    }

    @Override
    public String toString() {
        return "[T] [" + super.getStatusIcon() + "] "
                + this.description;
    }
}