package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A deadline object, has a name and a date of the deadline.
 */
public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * Constructor for a deadline task, which is not done.
     * @param name The name of deadline task
     * @param date The date of deadline task
     */
    public Deadline(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    /**
     * Constructor for a deadline task, in which the done status can be specified.
     * @param name The name of deadline task
     * @param date The date of deadline task
     * @param isDone The done status of deadline task
     */
    public Deadline(String name, LocalDateTime date, boolean isDone) {
        super(name, isDone);
        this.date = date;
    }

    /**
     * String to be displayed for a deadline task.
     * @return The string of deadline task to be displayed to user.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:ss a")) + ")";
    }

    /**
     * String to be returned when written and saved to drive for a deadline task.
     * @return The string of deadline task to be written to the file and saved.
     */
    public String writeDrive() {
        return "D|" + (super.isDone() ? "1|" : "0|") + this.name + "|" +
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHss"));
    }

    /**
     * A new deadline object with done property being set.
     * @return A new deadline object.
     */
    public Deadline setDone() {
        return new Deadline(this.name, this.date, true);
    }
}
