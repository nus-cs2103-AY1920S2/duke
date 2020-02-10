import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

/**
 * An Event task that extends from the Task class.
 * Events contain a LocalDate variable to represent the date the event is at.
 * Events also contain a dateConverter and dateFormatter to help format and convert
 * dates to the appropriate format.
 */
public class Event extends Task {

    protected LocalDate date;

    /**
     * Constructor for Event class.
     * @param description The description of the event.
     * @param date The date which the event is at.
     * @throws ParseException If the date cannot be parsed, i.e is in the wrong format.
     */
    public Event(String description, String date) throws DukeException {
        super(description);
        assert description != null : "description cannot be null";
        assert date != null : "date cannot be null";
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeException e) {
            throw new DukeException("Please format the date as YYYY-MM-DD.");
        }
    }

    /**
     * Returns the event date in a LocalDate variable.
     * @return The event date in a LocalDate variable.
     */
    public LocalDate getAt() {
        return this.date;
    }

    /**
     * Method to postpone event for the number of days specified by the user.
     * @param noDays Number of days to postpone said event.
     */
    public void snooze(int noDays) {
        Calendar cal = Calendar.getInstance();
        cal.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
        cal.add(cal.DATE, noDays);
        Date calendarDate = cal.getTime();
        date = new java.sql.Date(calendarDate.getTime()).toLocalDate();
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