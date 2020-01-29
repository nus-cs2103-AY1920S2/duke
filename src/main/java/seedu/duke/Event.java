package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Subclass of Task to represent an event.
 */
public class Event extends Task {
    protected LocalDate eventTime;

    /**
     * Subclass of Task to represent an event.
     *
     * @param description the details of the event
     * @param eventTime the date or time of the event
     */
    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        String formattedEventTime = " (at: " + this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        return "[E]" + super.toString() + formattedEventTime;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public LocalDate getTime() {
        return eventTime;
    }
}
