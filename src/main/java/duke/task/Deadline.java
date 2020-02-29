/**
 *  This is an Deadline class. Child class of Task.
 *  Stores an additional LocalDate variable of the Deadline.
 */

package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline class.
     * @param description Content of the Task.
     * @param by Deadline of the Task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}