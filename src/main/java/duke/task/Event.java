package duke.task;

import duke.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event type class extending from the
 * abstract <code>Task</code> class, which has a name and a <code>LocalDateTime</code> for the date and time.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class Event extends Task {
    private LocalDateTime datetime;

    /**
     * Event constructor with LocalDateTime for date.
     * @param  name of the deadline task.
     * @param datetime of the task.
     */
    public Event(String name, LocalDateTime datetime) {
        super(name);
        this.datetime = datetime;
    }

    /**
     * Event constructor with String for date.
     * @param  name of the deadline task.
     * @param datetime of the task.
     */
    public Event(String name, String datetime) {
        super(name);
        this.datetime = DateTimeUtil.stringAsDateTime(datetime);
    }

    /**
     * Getter for a reference to the LocalDateTime of the datetime.
     * @return the LocalDateTime representation of the datetime.
     */
    public LocalDateTime getDatetime() {
        return datetime;
    }

    /**
     * Gets the save-string representation of the task.
     * @return the String representation of the task Storage can save.
     */
    @Override
    public String toSaveString() {
        //E1Anna's Birthday@May 15
        return "E" + (isDone ? "1" : "0") + name + "@" + datetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy h.mma")) + ")";
    }
}
