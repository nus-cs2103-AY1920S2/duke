package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of Task that has a start time
 */
public class Event extends Task {
    public LocalDate startDate;

    /**
     * Constructs a Event with a description and marks it if it is done
     *
     * @param description The description of a Event gives additional details of the specific Event
     * @param startDate The time the Event begins or starts.
     * @param isDone A Event can be marked as done or finished.
     */
    public Event(String description, LocalDate startDate, boolean isDone) {
        super(description, isDone);
        this.startDate = startDate;
    }

    /**
     * Returns us a String representation of the Event 
     * useful for printing our task details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
