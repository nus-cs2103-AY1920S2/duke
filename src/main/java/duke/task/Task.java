package duke.task;

import java.time.LocalDate;
import java.util.Date;

/**
 * Interface for Task object.
 */
public interface Task {

    /**
     * Checks if task is done.
     * @return task done or not
     */
    public boolean isDone();

    /**
     * Gets task name.
     * @return task name
     */
    public String getTaskName();

    /**
     * Gets task type.
     * @return task type
     */
    public String getTaskType();

    /**
     * Gets task time.
     * @return task time
     */
    public LocalDate getTaskTime();

    /**
     * Marks task as done.
     */
    public void markAsDone();

    public void setTaskTime(LocalDate taskTime);
}
