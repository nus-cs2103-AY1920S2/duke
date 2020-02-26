package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class extends from Task class and has an extra property.
 */
public class Event extends Task {
    private LocalDate scheduledDate;

    /**
     * construct a Event instance by specify its timing.
     * @param description the description of the Event.
     * @param scheduledDate the timing of the Event.
     */
    public Event(String description, LocalDate scheduledDate) {
        super(description);
        this.scheduledDate = scheduledDate;
    }

    public LocalDate getScheduledDate() {
        return this.scheduledDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + scheduledDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
