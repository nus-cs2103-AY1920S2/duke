import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * An Event task that extends from the Task class.
 * Events contain a LocalDate variable to represent the date the event is at.
 * Events also contain a dateConverter and dateFormatter to help format and convert
 * dates to the appropriate format.
 */
public class Event extends Task {

    protected LocalDate date;
    protected DateTimeFormatter dateConverter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d YYYY");

    /**
     * Constructor for Event class.
     * @param description The description of the event.
     * @param date The date which the event is at.
     * @throws ParseException If the date cannot be parsed, i.e is in the wrong format.
     */
    public Event(String description, String date) throws ParseException {
        super(description);
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns the event date in a LocalDate variable.
     * @return The event date in a LocalDate variable.
     */
    public LocalDate getAt() {
        return this.date;
    }

    /**
     * Print the event task description, status icon and event date.
     * @return A string that indicates the task is a event, event description,
     *     status icon and event date.
     */
    @Override
    public String toString() {
        return "[E]" + " " + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d YYYY")) + ")";
    }
}