package duke.task;

import java.util.Date;

/**
 * Interface for Task object
 */
public interface Task {

    /**
     * check if task is done
     * @return task done or not
     */
    public boolean isDone();

    /**
     * get task name
     * @return task name
     */
    public String getTaskName();

    /**
     * get task type
     * @return task type
     */
    public String getTaskType();

    /**
     * get task time
     * @return task time
     */
    public Date getTaskTime();

    /**
     * mark task as done
     */
    public void markAsDone();
}
