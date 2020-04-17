import java.time.LocalDate;

/**
 * Represents an Event Task. A <code>Event</code> object corresponds to an Event to attend
 */
public class Event extends Task {
    protected LocalDate at;

    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns a string representation of the in the database format
     *
     * @return a string in the form "| 1 | description (at: 20-08-09)"
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + at
                + ")";
    }

    /**
     * Returns a string representation of the in the database format
     *
     * @return a string in the form "| 1 | description | 2020-08-09"
     */
    public String convert() {
        return "E"
                + super.convert()
                + " | "
                + at;
    }
}