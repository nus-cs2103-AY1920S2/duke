package dukebot.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

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