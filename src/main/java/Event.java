import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This represents an Event task, which is a subset of the class Task.
 * An event has a date associated with it.
 */

public class Event extends Task {

    protected LocalDate at;

    /**
     * Creates a new event.
     * @param description This is the description of the event.
     * @param at This is the date that the event is at.
     */

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the date the event is at.
     * @return The date the event is at.
     */

    public String getAt() {
        return this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
