/**
 * Event task that specifies an upcoming task on a certain date and time
 */
package duke.tasks;

import duke.parser.Parser;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {

    protected LocalDate eventDate;
    protected LocalTime eventTime;

    /**
     * Creates an Event task
     * @param taskTitle Title of upcoming task
     * @param eventDate Date of upcoming task
     * @param eventTime Time of upcoming task
     */
    public Event(String taskTitle, LocalDate eventDate, LocalTime eventTime) {
        super(taskTitle, eventDate, eventTime);
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    /**
     * Returns a string representation an Event task
     * @return A string representation an Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + Parser.outputDateFormatter.format(eventDate) + "-"
                + Parser.outputTimeFormatter.format(eventTime) + ")";
    }
}
