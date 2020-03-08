package duke.tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * A Deadline Task that takes in date.
 */
public class Deadline extends Task {
    protected LocalDate localDate;

    public Deadline(String description, String localDate) {
        super(description);
        this.localDate = LocalDate.parse(localDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String formatText() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + description + " | "
                        + localDate + "\n";
    }
}

