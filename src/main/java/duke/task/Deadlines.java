package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * The Class Deadlines is for tasks whose is a Deadline format.
 */
public class Deadlines extends Task {

    /**
     * The deadline date: the due date.
     */
    protected LocalDate deadLineDate;
    protected LocalTime deadLineTime;

    /**
     * Instantiates a new deadlines.
     *
     * @param description  the description
     * @param deadLineDate the deadline date
     */
    public Deadlines(String description, LocalDate deadLineDate, LocalTime deadLineTime) {
        super(description);
        this.deadLineDate = deadLineDate;
        this.deadLineTime = deadLineTime;
    }

    /**
     * Gets the deadline date.
     *
     * @return the deadline date
     */
    public LocalDate getDeadlineDate() {
        return this.deadLineDate;
    }

    /**
     * Gets the deadline time.
     *
     * @return the deadline time
     */
    public LocalTime getDeadlineTime() {
        return this.deadLineTime;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadLineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + this.deadLineTime.format(DateTimeFormatter.ofPattern(" h.mm a")) + ")\n";
    }
}
