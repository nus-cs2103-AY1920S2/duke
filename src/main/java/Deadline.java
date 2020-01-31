import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline task that extends from the Task class.
 * Deadlines contain a LocalDate variable to represent the date the deadline is due.
 * Deadlines also contain a dateConverter and dateFormatter to help format and convert
 * dates to the appropriate format.
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected DateTimeFormatter dateConverter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d YYYY");

    /**
     * Constructor for Deadline class.
     * @param description The description of the deadline task.
     * @param date The date which the deadline is due.
     * @throws ParseException If the date cannot be parsed, i.e is in the wrong format.
     */
    public Deadline(String description, String date) throws ParseException {
        super(description);
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns the date the deadline is due in a LocalDate variable.
     * @return The date the deadline is due.
     */
    public LocalDate getBy() {
        return this.date;
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