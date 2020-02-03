package dukebot.tasklist;

import java.time.LocalDateTime;

/**
 * Represents an Event.
 */
public class Event extends Task {

    /**
     * Generates a new Event.
     *
     * @param description  The name of the Event.
     * @param dateTime  Start time of Event.
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description, TaskType.Event, dateTime);
    }

    @Override
    public String toString() {
        return (this.description + " (at: " + this.dateTimeToString() + ")");
    }
}