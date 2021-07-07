package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import tasks.Tag;

/**
 * Task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {

    protected Date dateAt;

    /**
     * Creates a task that is an event.
     *
     * @param name   task that is an event to be completed.
     * @param dateAt date the event is at.
     * @oaram tags if present.
     */
    public Event(Name name, Date dateAt, Set<Tag> tags) {
        super(name, tags);
        this.dateAt = dateAt;
    }

    /**
     * Returns the string of the task that is an event indicating [E] for event followed by the name
     * then date of the event in MMM d yyyy.
     *
     * @return the string of the task that is an event indicating [E] for event followed by the name
     *     hen date of the event in MMM d yyyy.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateAt + ")";
    }
}