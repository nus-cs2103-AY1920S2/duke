package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task the user would record.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Constructs a new Event object.
     *
     * @param name Name of Event Task.
     * @param at Timing of the Event Task.
     */
    public Event(String name, LocalDate at) {
        super(name);
        this.at = at;
    }

    /**
     * Constructs a new Event object.
     *
     * @param name Name of Event Task.
     * @param at timing of the event task.
     * @param isDone Done status of the task.
     */
    public Event(String name, LocalDate at, boolean isDone) {
        super(name, isDone);
        this.at = at;
    }

    /**
     * Returns a string representation of the timing of the Event Task.
     *
     * @return Timing of the Event Task.
     */
    public String getAt() {
        return at.toString();
    }

    @Override
    public String getMnemonic() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + getMnemonic() + "]" + super.toString()
                + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyy")) +  ")";
    }
}
