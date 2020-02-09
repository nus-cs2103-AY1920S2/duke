package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task.
     * @param description string description of the deadline to be created
     * @param by due date in the format of yyyy-mm-dd
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toDatabaseString() {
        return "D" + "|" + (this.isDone ? "1" : "0") + "|" + this.description
                + "|" + this.by + "\n";
    }
}
