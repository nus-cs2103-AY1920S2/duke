import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A more detailed implementation of the Task class with specified date, start time and end time.
 */
public class Event extends Task {

    /**
     * The Date.
     */
    protected LocalDate date;
    /**
     * The Start time.
     */
    protected String startTime;
    /**
     * The End time.
     */
    protected String endTime;

    /**
     * Construct a new instance of Event.
     *
     * @param description the description.
     * @param date        the date.
     * @param startTime   the start time.
     * @param endTime     the end time.
     */
    public Event(String description, LocalDate date, String startTime, String endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + startTime + "-" + endTime + ")";
    }
}
