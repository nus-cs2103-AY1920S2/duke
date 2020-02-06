package duke;

import java.io.Serializable;

/**
 * Task with information of its description and status.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor that takes in description of task.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method returns status of task.
     *
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return tick or X symbols
    }

    /**
     * Method changes status of task to done.
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
