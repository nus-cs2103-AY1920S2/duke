package duke.task;

/**
 * {@code Task} is the abstract base class for all types of tasks. It encapsulates information about the task, namely,
 * a description of the task, and the completion status of the task.
 */
public abstract class Task {
    protected final String description;
    protected final boolean isCompleted;

    /**
     * Sets up a task with a description and completion status.
     *
     * @param description the description of the task
     * @param isCompleted the completion status of the task
     */
    protected Task(String description, boolean isCompleted) {
        assert description != null;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns {@code true} if the task is completed, {@code false} otherwise.
     *
     * @return the completion status of task
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Marks a task as completed.
     *
     * @return the completed task
     */
    public abstract Task complete();

    @Override
    public String toString() {
        return String.format("[%s] %s", isCompleted ? "✓" : "✘", description);
    }

    @Override
    public abstract boolean equals(Object obj);
}
