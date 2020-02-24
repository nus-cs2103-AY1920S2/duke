package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Event is a subtype of Task.
 * Event is meant to be for when an event is going to happen.
 * Eg. Dargo's Birthday 19/01
 *
 * @author Dargo
 */
public class Event extends Task {

    private LocalDate eventDate;
    private LocalTime eventTime;

    /**
     * Event subtype of Task.
     *
     * @param type Type of task.
     * @param task Input command for the task.
     * @throws DukeException When input command is not valid.
     */
    public Event(String type, String task) throws DukeException {
        super(type, task);

        try {
            String[] event = task.substring(task.indexOf("/")).split(" ");
            if(event.length != 3) {
                throw new DukeException("dateTime");
            }

            String eventDateString = event[1];
            String eventTimeString = event[2];
            this.eventDate = formatDate(eventDateString);
            this.eventTime = formatTime(eventTimeString);

        } catch(Exception e) {
            throw new DukeException("dateTime");
        }
    }

    /**
     * Returns the formatted string.
     *
     * @return Formatted string of task object in question.
     */
    @Override
    public String toString() {

        String checkmark = "N";

        if (this.isDone == true) {
            checkmark = "Y";
        }

        String output = "[" + this.type + "]" + "[" + checkmark + "] ";

        String taskName = task.substring(task.indexOf(" "), task.indexOf("/") - 1);
        output += taskName + " (by: " + eventDate.format(
                DateTimeFormatter.ofPattern("dd MMM yyyy")) + " " + eventTime.format(
                DateTimeFormatter.ofPattern("h:mm a")) + ")";
        return output;
    }
}

