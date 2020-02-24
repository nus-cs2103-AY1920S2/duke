package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class is a type of task.
 */
public class Event extends Task {
    /**
     * The constructor for the Event class.
     * @param description The description of the event.
     * @param date The date for the event.
     */
    public Event(String description, LocalDate date) {
        super(description, date);
    }

    /**
     * Gets the type of the task.
     * @return The type of the task.
     */
    public String getType() {
        return "[E]";
    }


    public String toString() {
        return getType() + getStatusIcon() + " " + description + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Generates a string when saving to the hard disk.
     * @return The string to be written to duke.txt.
     */
    public String saveString() {
        return "E|" + (isDone? "1|" : "0|") + description + "|" + date;
    }
}
