package duke;

import java.time.LocalDate;

/**
 * Deadline is a sub-class of Task. It contains an additional variable, date as compared to Task.
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructs a Deadline object.
     *
     * @param description Description of the Deadline.
     * @param date Due-date of the deadline.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the string representation of Deadline in the format [D][status] <description> (date: <date>).
     *
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        String newdate = String.format("%d %s %d", this.date.getDayOfMonth(), this.date.getMonth(), this.date.getYear());
        return "[D]" + super.toString() + " (by: " + newdate + ")";
    }

    /**
     * Returns the string representation of deadline to be stored in a file.
     * The format is D | <status> | <description> | <date>.
     *
     * @return String representation to be stored in a file.
     */
    @Override
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s", "D", this.isDone ? 1 : 0, this.description, convertDateToString(this.date));
    }
}