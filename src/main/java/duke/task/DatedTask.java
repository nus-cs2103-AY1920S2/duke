package duke.task;

import java.time.LocalDateTime;

/**
 * Represents the DatedTask type class extending from the
 * abstract <code>Task</code> class, which has a name and a <code>LocalDateTime</code> for the due date and time.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public abstract class DatedTask extends Task {
    private LocalDateTime date;

    /**
     * DatedTask constructor with LocalDateTime for date.
     *
     * @param name of the dated task.
     * @param date of the task.
     */
    public DatedTask(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    /**
     * Getter for a reference to the LocalDateTime of the date.
     *
     * @return the LocalDateTime representation of the date.
     */
    public LocalDateTime getDate() {
        return date;
    }
}
