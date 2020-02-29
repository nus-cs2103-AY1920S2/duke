package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event that extends from the <code>Task</code> class. It contains one additional <code>at</code>
 * attribute that which represents the date of the event.
 */
public class Event extends Task {

    protected LocalDate scheduledDate;

    /**
     * Constructs a event instance. The event is initialized as undone.
     * @param description The description of the event details.
     * @param scheduledDate The date of the event.
     */
    public Event(String description, String scheduledDate) {
        super(description);
        this.scheduledDate = LocalDate.parse(scheduledDate);
    }

    /**
     * Returns a string representation of the todo task in the data file.
     * @return string representation of the todo task in the data file.
     */
    @Override
    public String toStringInFile() {
        return "E # " + (isDone ? "1" : "0") + " # " + description + " # " + scheduledDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + scheduledDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
