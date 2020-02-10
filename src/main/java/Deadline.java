import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * A Deadline task that extends from the Task class.
 * Deadlines contain a LocalDate variable to represent the date the deadline is due.
 * Deadlines also contain a dateConverter and dateFormatter to help format and convert
 * dates to the appropriate format.
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected Calendar cal;

    /**
     * Constructor for Deadline class.
     * @param description The description of the deadline task.
     * @param date The date which the deadline is due.
     * @throws ParseException If the date cannot be parsed, i.e is in the wrong format.
     */
    public Deadline(String description, String date) throws DukeException {
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
     * Returns the date the deadline is due in a LocalDate variable.
     * @return The date the deadline is due.
     */
    public LocalDate getBy() {
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
     * Print the deadline task description, status icon and deadline date.
     * @return A string that indicates the task is a deadline, deadline description,
     *     status icon and deadline date.
     */
    @Override
    public String toString() {
        return "[D]" + " " + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d YYYY")) + ")";
    }
}