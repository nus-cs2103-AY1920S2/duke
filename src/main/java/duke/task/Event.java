package duke.task;

import java.util.Objects;

public class Event extends Task {
    /** The date of this event. */
    protected final String at;
    /** The start and end time of this event. */
    protected final String timeInterval;

    /**
     * Constructs a new event with a date, start time and end time.
     *
     * @param description the details of the deadline
     * @param at the date of the event.
     * @param timeInterval the start and end time of the event.
     */
    public Event(String description, String at, String timeInterval) {
        this(description, false, at, timeInterval);
    }

    private Event(String description, boolean isDone, String at, String timeInterval) {
        super(description, isDone);
        this.at = at;
        this.timeInterval = timeInterval;
    }

    @Override
    public Event markDone() {
        return new Event(description, true, at, timeInterval);
    }

    @Override
    public String serialize() {
        return serialize("E", at, timeInterval);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s %s)", super.toString(), at, timeInterval);
    }

    @Override
    public String toFormatString() {
        return String.format("[E]%s\nat:\n%s\n%s", super.toString(), at, timeInterval);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Event) {
            Event event = (Event)obj;
            return this.at.equals(event.at)
                    && this.timeInterval.equals(event.timeInterval);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), at, timeInterval);
    }
}