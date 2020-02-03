package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class extends from Task class and has an extra property.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * construct a Event instance by specify its timing.
     * @param description the description of the Event.
     * @param at the timing of the Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    public LocalDate getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
