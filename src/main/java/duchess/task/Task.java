package duchess.task;

/**
 * The {@code Task} class creates a task with a description and isCompleted state.
 */
public class Task {
    private boolean isCompleted;


    private String description;

    /**
     * Initialises the {@code Task} instance with its description.
     *
     * @param description Written description of the task.
     */
    Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Initialises the {@code Task} instance with its description and completion status.
     *
     * @param description Written description of the task.
     * @param isCompleted {@code boolean} value indicating whether the task is completed.
     */
    Task(String description, boolean isCompleted) {
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

    /**
     * Toggles the completion status of the task.
     */
    public void toggleIsCompleted() {
        this.isCompleted = !this.isCompleted;
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
