package duke.task;

import duke.exception.DukeException;

import java.time.format.DateTimeFormatter;

/**
 * An event task.
 */
public class Event extends Task {

    /**
     * Constructor for an event task.
     *
     * @param taskName Name of the task.
     * @param dateTime The date and time of the task (if any).
     */
    public Event(String taskName, String dateTime) throws DukeException {
        super(taskName.trim(), dateTime.trim());
        this.taskType = "E";
    }

    /**
     * Overloaded constructor for an event task.
     *
     * @param taskName Name of the task.
     * @param isDone Indicates if the task is completed already.
     * @param dateTime The date and time of the task (if any).
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