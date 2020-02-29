package seedu.duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an Event object.
 */
public class Event extends Task {
    protected Date eventDate;

    /**
     * Represents an Event object.
     *  @param description The details of the event.
     * @param eventDate The date of the event.
     */
    public Event(String description, Date eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d yyyy");
        String dateString = simpleDateFormat.format(eventDate);
        String formattedEventDate = " (at: " + dateString + ")";
        return "[E]" + super.toString() + formattedEventDate;
    }

    public Date getDate() {
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
