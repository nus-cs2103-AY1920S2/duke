package tasks;

import java.time.LocalDate;

/**
 * Abstracts time-based tasks.
 * Enables retrieval of general time of task.
 * Includes Event and Deadline.
 */
public abstract class DateTask extends Task{
    public DateTask(String description) {
        super(description);
    }

    /**
     * Retrieves the time set for the time-based task.
     */
    public abstract LocalDate getDate();
}
