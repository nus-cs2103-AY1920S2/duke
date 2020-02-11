package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that comes with a date.
 */
public abstract class DateTimeTask extends Task {
    protected LocalDate dateTime;

    public DateTimeTask(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns the date attached to the task.
     * @return Date in task.
     */
    public String getDateTime() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Sets the date attached to the task.
     * @param dateTime Date in task.
     */
    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }
}
