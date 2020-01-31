package duke.tasks;

import duke.tasks.Task;

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

        System.out.println(description);

        System.out.println("TESTING 123");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        this.description = arr[0];
        this.date = arr[1];
        this.localDate = LocalDate.parse(arr[1]);
    }

    public Deadline(String description, int done, String date) {
        super(description, done);

        this.description = description;
        this.date = date;
        this.localDate = LocalDate.parse(date);
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