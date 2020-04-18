package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An event is a special kind of task that needs to be done at a specific point of time.
 */
public class Event extends Task {
    protected String at = "";
    protected LocalDate todoDate;

    /**
     * Constructor of an event, similar to a Tasks's.
     * @param description The description of an event.
     * @param at the specific time or date that this event takes place.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * An overloading constructor with a special date parameter.
     * @param description The description of an event.
     * @param todoDate The date that this event takes place.
     */
    public Event(String description, LocalDate todoDate) {
        super(description);
        this.todoDate = todoDate;
    }

    /**
     * A getter method to access the time of the event.
     * @return A string denoting the event's time.
     */
    public String getTime() {
        if (this.at.equals("")) {
            return this.todoDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return this.at;
    }

    /**
     * Shows the string representation of the event, with the description and the time.
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        if (!this.at.equals("")) {
            return "[E]" + getDescription() + " (at: " + at + ")";
        } else {
            String dateToPrint = this.todoDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[E]" + getDescription() + " (at: " + dateToPrint + ")";
        }
    }
}