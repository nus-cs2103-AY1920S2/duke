package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a Task with date and/or time element(s), extending from the Task class. Deadline and Event classes extend
 * from this class.
 */
public abstract class TaskDate extends Task {
    public TaskDate(String description, boolean isDone, LocalDate date) {
        super(description, isDone, date);
    }

    public TaskDate(String description, boolean isDone, LocalDate date, LocalTime time) {
        super(description, isDone, date, time);
    }

    abstract LocalDate getDate();
}
