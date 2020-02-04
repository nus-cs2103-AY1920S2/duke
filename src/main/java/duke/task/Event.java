package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime datetime;

    /**
     * Constructor of the event object.
     * @param description The event description.
     * @param time The event date/time.
     */
    public Event(boolean isDone, String description, LocalDateTime datetime) {
        super(isDone, description);
        this.datetime = datetime;
    }

    public String toSaveFormat() {
        return String.format("E | %s | %s", super.toSaveFormat(), datetime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
            datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }
}