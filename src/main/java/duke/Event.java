package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An event object, has a name and a date of the event.
 */
public class Event extends Task {
    private LocalDateTime date;

    /**
     * Constructs an event task, which is not done.
     * @param name The name of event task
     * @param date The date of event task
     */
    public Event(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    /**
     * Constructs an event task, in which the done status can be specified.
     * @param name The name of event task
     * @param date The date of event task
     * @param isDone The done status of event task
     */
    public Event(String name, LocalDateTime date, boolean isDone) {
        super(name, isDone);
        this.date = date;
    }

    /**
     * Displays string for an event task.
     * @return The string of event task to be displayed to user.
     */
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:ss a")) + ")";
    }

    /**
     * Returns string to be written and saved to drive for an event task.
     * @return The string of event task to be written to the file and saved.
     */
    public String writeDrive() {
        return "E|" + (super.isDone() ? "1|" : "0|") + this.name + "|"
                + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHss"));
    }

    /**
     * Sets an event object to done status being done.
     * @return A new event object.
     */
    public Event setDone() {
        return new Event(this.name, this.date, true);
    }
}
