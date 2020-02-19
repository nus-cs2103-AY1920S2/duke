package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {

    protected LocalDate dateAt;

    /**
     * Creates a task that is an event.
     *
     * @param description task that is an event to be completed.
     * @param dateAt      date the event is at.
     */
    public Event(String description, LocalDate dateAt) {
        super(description);
        this.dateAt = dateAt;
    }

    public Event(String description, LocalDate dateAt, String tags) {
        super(description, tags);
        this.dateAt = dateAt;
    }

    /**
     * Returns the string of the task that is an event indicating [E] for event followed by the description
     * then date of the event in MMM d yyyy.
     *
     * @return the string of the task that is an event indicating [E] for event followed by the description
     * then date of the event in MMM d yyyy.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateAt.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}