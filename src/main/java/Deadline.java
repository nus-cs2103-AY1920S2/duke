import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task which has a description, a deadline date and a time.
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected String time;

    /**
     * Constructs a new Deadline instance.
     *
     * @param description Description of the task.
     * @param date Date when the task is due.
     * @param time Time when the task is due.
     */
    public Deadline(String description, LocalDate date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Overrides the Task's toString method
     * and it contains the mark, the description, as well as
     * the due date and time of the deadline.
     *
     * @return The String that represents the deadline's details.
     */
    @Override
    public String toString() {
        assert date != null : "A due date should be entered to this deadline.";
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + time + ")";
    }

}

