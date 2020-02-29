package seedu.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event object.
 */
public class Event extends Task {
    protected LocalDate eventDate;

    /**
     * Represents an Event object.
     *  @param description The details of the event.
     * @param eventDate The date of the event.
     */
    public Event(String description, LocalDate eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        String formattedEventDate = " (at: " + eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        return "[E]" + super.toString() + formattedEventDate;
    }

    public LocalDate getDate() {
        return eventDate;
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof Deadline) {
            int cmp = getDate().compareTo(((Deadline) task).getDate());
            if (cmp == 0) {
                return toString().compareTo(task.toString());
            } else {
                return cmp;
            }
        } else if (task instanceof Event) {
            int cmp = getDate().compareTo(((Event) task).getDate());
            if (cmp == 0) {
                return toString().compareTo(task.toString());
            } else {
                return cmp;
            }
        } else {
            return toString().compareTo(task.toString());
        }
    }
}
