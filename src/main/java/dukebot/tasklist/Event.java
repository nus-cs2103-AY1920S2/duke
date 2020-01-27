package dukebot.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Generates a new Event.
     *
     * @param description  The name of the Event.
     * @param dateTime  Start time of Event.
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description, TaskType.Event, dateTime);
    }
    //    public String getTime() {
    //        return (this.time);
    //    }

    @Override
    public String toString() {
        return (this.description + " (at: " + this.dateTimeToString() + ")");
    }
}