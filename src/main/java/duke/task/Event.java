package duke.task;

import duke.exception.DukeException;

import java.time.format.DateTimeFormatter;

/** An event task. */
public class Event extends Task {

    /**
     * Constructs an event.
     *
     * @param taskName Name of the task.
     * @param dateTime The date and time of the task (if any).
     * @throws DukeException If date format is incorrect.
     */
    public Event(String taskName, String dateTime) throws DukeException {
        super(taskName.trim(), dateTime.trim());
        this.taskType = "E";
    }

    /**
     * Constructs an event with additional details.
     *
     * @param taskName Name of the task.
     * @param isDone Indicates if the task is completed already.
     * @param dateTime The date and time of the task (if any).
     * @throws DukeException If date format is incorrect.
     */
    public Event(String taskName, boolean isDone, String dateTime) throws DukeException {
        super(taskName.trim(), isDone, dateTime.trim());
        this.taskType = "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime.format(DateTimeFormatter.ofPattern("dd MM yyyy")) + ")";
    }

}