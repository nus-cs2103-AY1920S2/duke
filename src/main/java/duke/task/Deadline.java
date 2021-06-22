package duke.task;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

import duke.exception.DuchessException;
import duke.util.DateTimeStringFormatter;

/**
 * The {@code Deadline} class extends from {@code Task} to allow the setting of
 * a {@code LocalDateTime deadline}.
 */
public class Deadline extends Task {
    protected static final String DEADLINE_SYMBOL = "[D]";
    protected LocalDateTime deadline;
    protected boolean isCompletedOnTime;

    /**
     * Initialises the {@code Deadline} instance with its description and deadline.
     *
     * @param description Written description of the task.
     * @param deadline    {@code LocalDateTime} object indicating the deadline of
     *                    the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Initialises the {@code Deadline} instance with all of its information. Mainly used
     * by {@code Storage} to regenerate instances.
     *
     * @param description    Written description of the task.
     * @param deadline       {@code LocalDateTime} object indicating the deadline of
     *                       the task.
     * @param isCompleted    {@code boolean} value indicating whether the task is
     *                       completed.
     * @param creationTime   {@code LocalDateTime} object indicating the time of creation of
     *                       the task.
     * @param completionTime {@code LocalDateTime} object indicating the time of
     *                       completion of the task.
     */
    public Deadline(String description, LocalDateTime deadline, boolean isCompleted, LocalDateTime creationTime,
                    LocalDateTime completionTime, boolean isCompletedOnTime) {
        super(description, isCompleted, creationTime, completionTime);
        this.deadline = deadline;
        this.isCompletedOnTime = isCompletedOnTime;
    }

    /**
     * Returns the deadline of the {@code Deadline}.
     *
     * @return Deadline in {@code LocalDateTime} format.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Snoozes the deadline by the given amount of time.
     *
     * @param snoozePeriod The amount of time to snooze the deadline for.
     */
    public void snooze(TemporalAmount snoozePeriod) {
        this.deadline = this.deadline.plus(snoozePeriod);
    }

    @Override
    public String toString() {
        return DEADLINE_SYMBOL + super.toString() + " (by: "
                + DateTimeStringFormatter.formatDateTime(this.deadline, this.isCompleted) + ")";
    }

    @Override
    protected Object clone() throws DuchessException {
        Deadline clonedDeadline = (Deadline) super.clone();
        clonedDeadline.deadline = this.deadline; // as LocalDateTime is immutable
        clonedDeadline.isCompletedOnTime = this.isCompletedOnTime;
        return clonedDeadline;
    }

    @Override
    public void completeTask() {
        super.completeTask();
        this.isCompletedOnTime = this.completionTime.isAfter(this.deadline);
    }
}
