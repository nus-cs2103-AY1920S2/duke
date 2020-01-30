package duchess.task;

/**
 * The {@code ToDo} class extends from {@code Task}.
 */
public class ToDo extends Task {
    /**
     * Initialises the {@code ToDo} instance with its description.
     *
     * @param description Written description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Initialises the {@code ToDo} instance with its description and
     * completion status.
     *
     * @param description Written description of the task.
     * @param isCompleted {@code boolean} value indicating whether the task is completed.
     */
    public ToDo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
