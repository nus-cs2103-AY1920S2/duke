package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that extends from the <code>Task</code> class. It contains one additional <code>by</code>
 * attribute which represents the due date of the task.
 */
public class Deadline extends Task {

    protected LocalDate dueDate;

    /**
     * Constructs a deadline task instance. The deadline task is initialized as undone.
     * @param description The description of the deadline task details.
     * @param dueDate The due date of the deadline task.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = LocalDate.parse(dueDate);
    }

    /**
     * Returns a string representation of the todo task in the data file.
     * @return string representation of the todo task in the data file.
     */
    @Override
    public String toStringInFile() {
        return "D # " + (isDone ? "1" : "0" + " # ") + description + " # " + dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}