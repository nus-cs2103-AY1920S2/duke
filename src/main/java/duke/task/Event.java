package duke.task;

import java.util.Objects;

public class Event extends Task {
    /** The date of this event. */
    protected final String at;
    /** The start and end time of this event. */
    protected final String timeSlot;

    /**
     * Constructs a new event with a date, start time and end time.
     *
     * @param description the details of the deadline
     * @param at the date of the event.
     * @param timeSlot the start and end time of the event.
     */
    public Event(String description, String at, String timeSlot) {
        this(description, false, at, timeSlot);
    }

    private Event(String description, boolean isDone, String at, String timeSlot) {
        super(description, isDone);
        this.at = at;
        this.timeSlot = timeSlot;
    }

    @Override
    public Event markDone() {
        return new Event(description, true, at, timeSlot);
    }

    @Override
    public String serialize() {
        return serialize("E", at, timeSlot);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s %s)", super.toString(), at, timeSlot);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Event) {
            Event event = (Event)obj;
            return this.at.equals(event.at)
                    && this.timeSlot.equals(event.timeSlot);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), at, timeSlot);
    }
}