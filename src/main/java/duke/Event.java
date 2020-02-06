package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is a task with a specific date and time.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Constructor that takes in description and date time of task.
     *
     * @param description
     * @param at
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }
}
