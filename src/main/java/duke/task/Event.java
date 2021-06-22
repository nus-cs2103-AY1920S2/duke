package duke.task;

import java.time.LocalDateTime;

import duke.exception.DuchessException;

/**
 * The {@code Event} class extends from {@code Task} to allow
 * the setting of a {@code String timeFrame}.
 */
public class Event extends Task {
    private static final String EVENT_SYMBOL = "[E]";
    private String timeFrame;

    /**
     * Initialises the {@code Event} instance with its description
     * and timeFrame.
     *
     * @param description Written description of the task.
     * @param timeFrame   {@code String} indicating the timeFrame of the task.
     */
    public Event(String description, String timeFrame) {
        super(description);
        this.timeFrame = timeFrame;
    }

    /**
     * Initialises the {@code Event} instance with all of its information. Mainly used
     * by {@code Storage} to regenerate instances.
     *
     * @param description    Written description of the task.
     * @param timeFrame      {@code String} indicating the timeFrame of the task.
     * @param isCompleted    {@code boolean} value indicating whether the task is completed.
     * @param creationTime   {@code LocalDateTime} object indicating the time of creation of
     *                       the task.
     * @param completionTime {@code LocalDateTime} object indicating the time of
     *                       completion of the task.
     */
    public Event(String description, String timeFrame, boolean isCompleted,
                 LocalDateTime creationTime, LocalDateTime completionTime) {
        super(description, isCompleted, creationTime, completionTime);
        this.timeFrame = timeFrame;
    }

    /**
     * Returns the timeFrame of the {@code Event}.
     *
     * @return TimeFrame of the {@code Event}.
     */
    public String getTimeFrame() {
        return timeFrame;
    }

    @Override
    public String toString() {
        return EVENT_SYMBOL + super.toString() + " (at: " + this.timeFrame + ")";
    }

    @Override
    protected Object clone() throws DuchessException {
        Event clonedEvent = (Event) super.clone();
        clonedEvent.timeFrame = this.timeFrame; // String is immutable
        return clonedEvent;
    }
}
