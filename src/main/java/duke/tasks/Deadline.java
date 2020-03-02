package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.enums.ErrorCodes;
import duke.exceptions.DukeException;
import duke.tags.Tag;

/**
 * Represents a Deadline task.
 *
 * @author Firzan Armani
 */
public class Deadline extends Task {
    private LocalDate parsedDate;

    /**
     * Deadline constructor.
     *
     * @param taskName Name of the task
     * @param taskDate LocalDate object of the deadline
     * @param tags ArrayList of Tags for the task
     * @throws DukeException Incorrect format/missing date
     */
    public Deadline(String taskName, LocalDate taskDate, ArrayList<Tag> tags) throws DukeException {
        super(taskName);
        this.setTags(tags);
        if (taskDate == null) {
            throw new DukeException(ErrorCodes.MISSING_DEADLINE_DATE);
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
        return "[D]" + super.toString() + " (by: "
            + this.parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + parsedDate.toString();
    }
}