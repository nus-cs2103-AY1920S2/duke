/**
 *  This is an Event class. Child class of Task.
 *  Stores an additional LocalDate variable of the Event.
 */

package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Event class.
     * @param description Content of the Task.
     * @param at Date of Event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
