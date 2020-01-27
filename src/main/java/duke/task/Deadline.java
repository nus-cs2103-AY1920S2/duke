package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor of Deadline object.
     *
     * @param description the title of the task.
     * @param by the deadline of the task in yyyy-MM-dd format.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * For loading from disk used in Storage class.
     *
     * @param description the title of the task.
     * @param isDone whether the task has been completed
     * @param by the deadline of the task.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Formats the Deadline task to be saved in the disk.
     *
     * @return the String to be saved in the disk.
     */
    @Override
    public String formatSavingName() {
        return "deadline," + description + "," + isDone + "," + by + "\n";
    }

    /**
     * For printing of the Deadline object.
     *
     * @return the string representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}