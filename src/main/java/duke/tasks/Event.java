package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.enums.ErrorCodes;
import duke.exceptions.DukeException;

public class Event extends Task {
    private String eventDate;
    private LocalDate parsedDate;

    /**
     * Event constructor.
     *
     * @param eventArgs Joined string of the user input command arguements
     * @throws DukeException Incorrect format/missing date
     */
    public Event(String eventArgs) throws DukeException {
        super(eventArgs.split(" /at ")[0]);
        try {
            this.eventDate = eventArgs.split(" /at ")[1];
            this.parsedDate = LocalDate.parse(eventDate);
        } catch (DateTimeParseException e) {
            throw new DukeException(ErrorCodes.INVALID_DATE_FORMAT);
        } catch (Exception e) {
            throw new DukeException(ErrorCodes.MISSING_EVENT_DATE);
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
        return "E" + super.toFileString() + " | " + eventDate;
    }
}