package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of Task that has a due date
 */
public class Deadline extends Task {
    public LocalDate dueDate;
    /**
     * Constructs a Deadline with a description and marks it if it is done
     *
     * @param description The description of a Deadline gives additional details of the specific Deadline
     * @param dueDate The time the Deadline must be submited by.
     * @param isDone A Deadline can be marked as done or finished.
     */
     public Deadline(String description, LocalDate dueDate, boolean isDone) {
          super(description, isDone);
          this.dueDate = dueDate;
     }

    /**
     * Returns us a String representation of the Deadline
     * useful for printing our task details.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
