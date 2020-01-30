package duchess.task;

/**
 * The {@code Event} class extends from {@code Task} to allow
 * the setting of a {@code String timeFrame}.
 */
public class Event extends Task {
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
     * Initialises the {@code Event} instance with its description, timeFrame
     * and completion status.
     *
     * @param description Written description of the task.
     * @param timeFrame   {@code String} indicating the timeFrame of the task.
     * @param isCompleted {@code boolean} value indicating whether the task is completed.
     */
    public Event(String description, String timeFrame, boolean isCompleted) {
        super(description, isCompleted);
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
        return "[E]" + super.toString() + " (at: " + this.timeFrame + ")";
    }
}
