package duke.task;

/**
 * {@code Event} is a subclass of {@code Task}. It represents a task with a description and completion status, as well
 * as the time of the event.
 */
public class Event extends Task {
    protected final String time;

    /**
     * Constructs an {@code Event} object with the specified description and time, marked as incomplete.
     *
     * @param description the description of the event
     * @param time the time of the event
     */
    public Event(String description, String time) {
        super(description, false);
        this.time = time;
    }

    /**
     * Constructs an {@code Event} object with the specified description, time, and completion status.
     *
     * @param description the description of the event
     * @param time the time of the event
     * @param isCompleted the completion status of the event
     */
    public Event(String description, String time, boolean isCompleted) {
        super(description, isCompleted);
        this.time = time;
    }

    @Override
    public Event complete() {
        return new Event(description, time, true);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }
}
