package duke.tasks;

import duke.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents an deadline task.
 */
public class Deadline extends Task {

    public String date;
    public LocalDate localDate;

    /**
     * Creates an incomplete deadline task with given description.
     *
     * @param description description of task.
     */
    public Deadline(String description) {
        super(description);
        String[] arr = description.split(" /by ");

        this.description = arr[0];
        this.date = arr[1];
        this.localDate = LocalDate.parse(arr[1]);
    }

    /**
     * Creates a deadline task.
     *
     * @param description description of task.
     * @param done boolean indicating if task is completed.
     */
    public Deadline(String description, int done) {
        super(description, done);
        String[] arr = description.split(" /by ");

        this.description = arr[0];
        this.date = arr[1];
        this.localDate = LocalDate.parse(arr[1]);
    }

    /**
     * Creates a deadline task.
     *
     * @param description description of task.
     * @param done boolean indicating if task is completed.
     * @param date deadline of task.
     */
    public Deadline(String description, int done, String date) {
        super(description, done);

        this.description = description;
        this.date = date;
        this.localDate = LocalDate.parse(date);
    }

    /**
     * Formats deadline task for save to database.
     *
     * @return formatted string that represents task.
     */
    public String toPrint() {
        if (this.isDone) {
            return "D | " + 1 + " | " + this.description + " | "
                    + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return "D | " + 0 + " | " + this.description + " | "
                    + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    /**
     * Formats deadline task for printing.
     *
     * @return formatted string that represents task.
     */
    public String toString() {
        if (this.isDone) {
            return "[D][✓] " + this.description + " (by: "
                    + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D][✗] " + this.description + " (by: "
                    + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
}