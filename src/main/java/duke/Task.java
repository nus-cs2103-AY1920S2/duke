package duke;

import java.io.Serializable;

/**
 * Task with information of its description and status.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Takes in description of task.
     *
     * @param description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status of task.
     *
     * @return status of whether a task is done
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Changes status of task to done.
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
