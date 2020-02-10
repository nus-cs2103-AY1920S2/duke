package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A more detailed implementation of the task.Task class with specified deadline.
 */
public class Deadline extends Task {

    /**
     * The deadline.
     */
    protected LocalDate date;

    /**
     * Constructs a new instance of task.Deadline.
     *
     * @param description the description of the task.
     * @param date        the deadline of the task.
     */
    public Deadline(String description, LocalDate date) {

        super(description);
        this.date = date;
    }

    public LocalDate getDate() {

        return date;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
