package hakunamatata.task;

import java.time.LocalDateTime;

import hakunamatata.Parser;

/**
 * Represents an event task.
 *
 * @author Wang Yuting
 */
public class Event extends Task {

    /**
     * This is the date and time of the event.
     */
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Creates a new <code>Event</code> with the given <code>description</code> and <code>at</code> (date and time).
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates a new <code>Event</code> with the given <code>description</code>, <code>at</code> (date and time) and
     * <code>isDone</code>.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, TaskType.EVENT);
        this.isDone = isDone;
        this.start = start;
        this.end = end;
    }

    /**
     * Gets start date and time.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Gets end date and time.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Converts the <code>Event</code> detail into a String.
     *
     * @return The detail of <code>Event</code> in String.
     */
    @Override
    public String toString() {
        String at = Parser.getDateTimeString(this.start) + " - " + Parser.getDateTimeString(this.end);
        return "[E]" + super.toString()
                + " (at: " + at + ")";
    }
}

