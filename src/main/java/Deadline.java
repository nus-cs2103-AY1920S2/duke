package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public String date;
    public LocalDate localDate;

    public Deadline(String description) {
        super(description);
        String[] arr = description.split(" /by ");
        this.description = arr[0];
        this.date = arr[1];
        this.localDate = LocalDate.parse(arr[1]);
    }

    public Deadline(String description, int done) {
        super(description, done);
        String[] arr = description.split(" /by ");
        this.description = arr[0];
        this.date = arr[1];
        this.localDate = LocalDate.parse(arr[1]);
    }

    public String toPrint() {
        if (this.isDone) {
            return "D | " + 1 + " | " + this.description + " | " + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return "D | " + 0 + " | " + this.description + " | " + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    public String toString() {
        if (this.isDone) {
            return "[D][✓] " + this.description + " (by: " + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D][✗] " + this.description + " (by: " + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
}