/**
 * Represents an event task. A <code>Event</code> object
 * corresponds to an event task represented by the description and location e.g.,
 * <code>"read", "school"</code>
 */
public class Event extends Task {

    String at;

    /**
     * Constructor for creating event objects.
     * @param description description of event task
     * @param at location of task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
