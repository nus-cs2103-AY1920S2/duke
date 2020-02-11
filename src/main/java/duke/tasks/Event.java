/**
 * Event task that specifies an upcoming task on a certain date and time
 */
package duke.tasks;

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
        super(taskTitle);
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
                + Task.dateFormatter.format(eventDate) + "-"
                + Task.timeFormatter.format(eventTime) + ")";
    }

    /**
     * Returns a string representation of the date of this task
     * @return A string representation of the date of this task
     */
    public String getDate() {
        return Task.dateFormatter.format(eventDate);
    }

    /**
     * Returns a string representation of the time of this task
     * @return A string representation of the time of this task
     */
    public String getTime() {
        return Task.timeFormatter.format(eventTime);
    }
}
