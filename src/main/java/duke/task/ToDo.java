package duke.task;

import java.time.LocalDateTime;

/**
 * The {@code ToDo} class extends from {@code Task}.
 */
public class ToDo extends Task {
    private static final String TO_DO_SYMBOL = "[T]";

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
     * @param description    Written description of the task.
     * @param isCompleted    {@code boolean} value indicating whether the task is completed.
     * @param completionTime {@code LocalDateTime} object indicating the time of
     *                       completion of the task.
     */
    public ToDo(String description, boolean isCompleted, LocalDateTime completionTime) {
        super(description, isCompleted, completionTime);
    }

    @Override
    public String toString() {
        return TO_DO_SYMBOL + super.toString();
    }
}
