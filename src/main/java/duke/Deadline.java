package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dueDate;

    /**
     * Constructs a Deadline task.
     * @param description string description of the deadline to be created
     * @param dueDate due date in the format of yyyy-mm-dd
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toDatabaseString() {
        return "D" + "|" + (this.isDone ? "1" : "0") + "|" + this.description
                + "|" + this.dueDate + "\n";
    }
}
