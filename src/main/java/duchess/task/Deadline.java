package duchess.task;

import duchess.util.DateTimeStringFormatter;

import java.time.LocalDateTime;

/**
 * The {@code Deadline} class extends from {@code Task} to allow
 * the setting of a {@code LocalDateTime deadline}.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Initialises the {@code Deadline} instance with its description
     * and deadline.
     *
     * @param description Written description of the task.
     * @param deadline    {@code LocalDateTime} object indicating the deadline of the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Initialises the {@code Deadline} instance with its description, deadline
     * and completion status.
     *
     * @param description Written description of the task.
     * @param deadline    {@code LocalDateTime} object indicating the deadline of the task.
     * @param isCompleted {@code boolean} value indicating whether the task is completed.
     */
    public Deadline(String description, LocalDateTime deadline, boolean isCompleted) {
        super(description, isCompleted);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of the {@code Deadline}.
     *
     * @return Deadline in {@code LocalDateTime} format.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + DateTimeStringFormatter.formatDateTime(this.deadline, this.isCompleted())
                + ")";
    }
}
