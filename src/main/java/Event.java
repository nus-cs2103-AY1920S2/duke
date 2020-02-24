import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event object for tasks that start and ends at a specific time.
 */


public class Event extends Task {
    private LocalDate at;

    /**
     * Creates a new Event object.
     * @param content Description of event.
     * @param at Date when event is held.
     */
    public Event(String content, String at) {
        super(content);
        this.at = LocalDate.parse(at);
    }

    /**
     * Updates the event date.
     * @param date New date of when the event is held.
     */

    @Override
    public void updateDate(String date) {
        this.at = LocalDate.parse(date);
    }

    /**
     * Returns a string describing the event in the format for saving.
     * @return String describing the event.
     */
    @Override
    public String toStore() {
        return "[E]" + super.toStore() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
    }

    /**
     * Returns a string describing the event in the format for printing.
     * @return String describing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyy")) + ")";
    }
}
