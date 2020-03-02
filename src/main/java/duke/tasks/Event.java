package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.enums.ErrorCodes;
import duke.exceptions.DukeException;
import duke.tags.Tag;

/**
 * Represents a Event task.
 *
 * @author Firzan Armani
 */
public class Event extends Task {
    private LocalDate parsedDate;

    /**
     * Event constructor.
     *
     * @param taskName Name of the task
     * @param taskDate LocalDate object of the event
     * @param tags ArrayList of Tags for the task
     * @throws DukeException Incorrect format/missing date
     */
    public Event(String taskName, LocalDate taskDate, ArrayList<Tag> tags) throws DukeException {
        super(taskName);
        this.setTags(tags);
        if (taskDate == null) {
            throw new DukeException(ErrorCodes.MISSING_EVENT_DATE);
        } else {
            this.parsedDate = taskDate;
        }
    }

    /**
     * A method to return the details of the current Task object.
     * Contains the task done status and the task name.
     *
     * @return A String representation of the task details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
            + this.parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + parsedDate.toString();
    }
}