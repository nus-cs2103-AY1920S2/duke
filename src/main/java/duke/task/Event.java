package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing an Event.
 */
public class Event extends Task {

    /**
     * Creates Event object.
     *
     * @param description String representing the task that needs to be done
     * @param time LocalDateTime representing the time event is at.
     */
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns String representation of the event object.
     *
     * @return String representation of the event to print.
     */
    @Override
    public String toString() {
        String timeStr = this.time.format(DateTimeFormatter.ofPattern("HH:mm, MMM d yyyy"));
        return String.format("[E]%s (at: %s)", super.toString(), timeStr);
    }
    
    /**
     * Returns String representation of the event object to be saved.
     *
     * @return String representation of the event to save.
     */
    @Override
    public java.lang.String toSaveString() {
        String timeStr = this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return String.format("%s || event || %s || %s", super.toSaveString(), this.description, timeStr);
    }
}
