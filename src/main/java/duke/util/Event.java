package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event task.
 *
 * <p>CS2103T AY19/20 Semester 2
 * Individual Duke project
 * 29 Jan 2020
 *
 * @author Jel
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Constructs an Event instance.
     * @param description The description or details of the Event.
     * @param at The date where the Event will happen.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public LocalDate getScheduledTime() {
        return this.at;
    }

    /**
     * Overrides the Object's toString method
     * and contains the task identifier, status icon, description,
     * and scheduled time of the event.
     * @return The String that containing the Event's details.
     */
    @Override
    public String toString() {
        assert at != null : "At date does not exist.";
        return String.format("[E]%s (at: %s)", super.toString(), 
                             this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
