package duke.task;

import java.time.LocalDate;

/**
 * Represents a Task with date and/or time element(s), extending from the Task class. Deadline and Event classes extend
 * from this class.
 */
public abstract class TaskDate extends Task {
    public TaskDate(String description, boolean isDone) {
        super(description, isDone);
    }

    abstract LocalDate getDate();
}
