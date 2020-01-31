package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructs an Event object.
     *
     * @param description the title of the event.
     * @param at the date of the event in yyyy-MM-dd format.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructs an Event object when loading from disk.
     *
     * @param description the title of the event.
     * @param isDone whether the event is over.
     * @param at the date of the event.
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Formats the Event task to be saved in the disk.
     *
     * @return the String to be saved in the disk.
     */
    @Override
    public String formatSavingName() {
        return "event," + description + "," + isDone + "," + at + "\n";
    }

    /**
     * Returns the string representation of the Event object.
     *
     * @return the string representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
