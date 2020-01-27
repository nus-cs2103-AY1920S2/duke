package duke.task;

/**
 * Represents a Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of a Task
     *
     * @param description the title of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * For loading from disk used in Storage class.
     *
     * @param description the title of the task.
     * @param isDone whether the task has been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public abstract String formatSavingName();

    /**
     * Get the string representation of whether the task has been completed.
     *
     * @return [Y] if task is completed, [N] if task is not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "[Y]" : "[N]");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + this);
    }

    /**
     * For printing of the Task object.
     *
     * @return the string representation of Task.
     */
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}