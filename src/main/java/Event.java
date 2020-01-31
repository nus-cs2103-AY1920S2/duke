package duke.tasks;

import duke.tasks.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class represents an event task.
 */
public class Event extends Task {
    public String date;
    public LocalDate localDate;

    /**
     * Creates an incomplete event task with given description.
     *
     * @param description description of task.
     */
    public Event(String description) {
        super(description);
        String[] arr = description.split(" /at ");
        this.description = arr[0];
        this.date = arr[1];
        this.localDate = LocalDate.parse(arr[1]);
    }

    /**
     * Creates an event task.
     *
     * @param description description of task.
     * @param done boolean indicating if task is completed.
     */
    public Event(String description, int done) {
        super(description, done);
        String[] arr = description.split(" /at ");
        this.description = arr[0];
        this.date = arr[1];
        this.localDate = LocalDate.parse(arr[1]);
    }

    /**
     * Creates an event task.
     *
     * @param description description of task.
     * @param done boolean indicating if task is completed.
     * @param date date of event.
     */
    public Event(String description, int done, String date) {
        super(description, done);

        this.description = description;
        this.date = date;
        this.localDate = LocalDate.parse(date);
    }

    /**
     * Formats event task for save to database.
     *
     * @return formatted string that represents task.
     */
    public String toPrint() {
        if (this.isDone) {
            return "E | " + 1 + " | " + this.description + " | " + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return "E | " + 0 + " | " + this.description + " | " + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    /**
     * Formats event task for printing.
     *
     * @return formatted string that represents task.
     */
    public String toString() {
        if (this.isDone) {
            return "[E][✓] " + this.description + " (at: " + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[E][✗] " + this.description + " (at: " + this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
}