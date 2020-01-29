package duke.task;

import duke.exception.DukeException;

import java.time.format.DateTimeFormatter;

/** A deadline task. */
public class Deadline extends Task {

    /**
     * Constructs a deadline task.
     *
     * @param taskName Name of the task.
     * @param dateTime The date and time of the task (if any).
     * @throws DukeException If date format is incorrect.
     */
    public Deadline(String taskName, String dateTime) throws DukeException {
        super(taskName.trim(), dateTime.trim());
        this.taskType = "D";
    }

    /**
     * Constructs a deadline task with additional details.
     *
     * @param taskName Name of the task.
     * @param isDone Indicates if the task is completed already.
     * @param dateTime The date and time of the task (if any).
     * @throws DukeException If date format is incorrect.
     */
    public Deadline(String taskName, boolean isDone, String dateTime) throws DukeException {
        super(taskName.trim(), isDone, dateTime.trim());
        this.taskType = "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime.format(DateTimeFormatter.ofPattern("dd MM yyyy")) + ")";
    }

}