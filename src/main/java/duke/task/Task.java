package duke.task;

import duke.exception.DuchessException;

import static duke.util.MagicStrings.ERROR_CANNOT_UNDO;

/**
 * The {@code Task} class creates a task with a description and isCompleted state.
 */
public class Task implements Cloneable {
    protected boolean isCompleted;
    protected String description;

    /**
     * Initialises the {@code Task} instance with its description.
     *
     * @param description Written description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Initialises the {@code Task} instance with its description and completion status.
     *
     * @param description Written description of the task.
     * @param isCompleted {@code boolean} value indicating whether the task is completed.
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    private String getStatusIcon() {
        return (this.isCompleted ? "\u2713" : "\u2718"); // tick or cross depending on isCompleted
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    protected Object clone() throws DuchessException {
        Task clonedTask = null;
        try {
            clonedTask = (Task) super.clone();
            clonedTask.description = this.description; // Safe due to immutability of strings.
            clonedTask.isCompleted = this.isCompleted;
            return clonedTask;
        } catch (CloneNotSupportedException e) {
            throw new DuchessException(ERROR_CANNOT_UNDO);
        }
    }

    /**
     * Toggles the completion status of the task.
     */
    public void completeTask() {
        this.isCompleted = true;
    }

    /**
     * Returns a {@code boolean} value that indicates the completion status of the task.
     *
     * @return Completion status of the task.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Returns a description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }
}
