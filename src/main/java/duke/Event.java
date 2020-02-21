package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task created by the user.
 */
public class Event extends Task {

    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
