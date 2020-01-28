package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representation of a event with a time.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Event constructor.
     * @param description desc
     * @param at at
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * Overloaded event constructor.
     * @param description desc
     * @param at at
     * @param mark mark
     */
    public Event(String description, String at, boolean mark) {
        super(description, mark);
        this.at = LocalDate.parse(at);
    }

    /**
     * Custom toString implementation.
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at
                .format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Override saveFormat method to generate neatly formatted information for saving.
     * @return String
     */
    @Override
    public String saveFormat() {
        return "E" + " , " + (super.isDone ? "1" : "0") + " , " + super.description + " , "
                + this.at;
    }
}
