package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * One of the tasks that could be created by user.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Event constructor.
     *
     * @param description Describes the task.
     * @param at When the task occurs.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public LocalDate getDate() {
        return at;
    }

    /**
     * Overwritten toString to fit format requirements.
     *
     * @return Formatted string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}