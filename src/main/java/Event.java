import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task which has a description, a date and a time.
 */
public class Event extends Task {

    protected LocalDate date;
    protected String time;

    /**
     * Constructs a new Event instance.
     *
     * @param description Description of the task.
     * @param date Date when the task is due.
     * @param time Time when the task is due.
     */
    public Event(String description, LocalDate date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Overrides the Task's toString method
     * and it contains the mark, the description, as well as
     * the due date and time of the event.
     *
     * @return The String that represents the event's details.
     */
    @Override
    public String toString() {
        assert time != null : "A due time should be entered to this event.";
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + time + ")";
    }

}
