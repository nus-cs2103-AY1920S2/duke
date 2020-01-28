package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * A deadline task.
 */
public class Deadline extends Task {

    /**
     * Constructor for a deadline task.
     *
     * @param taskName Name of the task.
     * @param dateTime The date and time of the task (if any).
     */
    public Deadline(String taskName, String dateTime) {
        super(taskName.trim(), dateTime.trim());
        this.taskType = "D";
    }

    /**
     * Overloaded constructor for a deadline task.
     *
     * @param taskName Name of the task.
     * @param isDone Indicates if the task is completed already.
     * @param dateTime The date and time of the task (if any).
     */
    public Deadline(String taskName, boolean isDone, String dateTime) {
        super(taskName.trim(), isDone, dateTime.trim());
        this.taskType = "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime.format(DateTimeFormatter.ofPattern("dd MM yyyy")) + ")";
    }

}