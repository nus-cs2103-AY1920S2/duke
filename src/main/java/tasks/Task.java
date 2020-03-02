package tasks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstraction of what something the user wants to do.
 * Implements Serializable to be saved as a stream of bytes.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task object.
     * @param description How the user describes the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter for description of the task.
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns an string icon that suggests if a task is completed.
     * @return String that represents either a tick or cross.
     */
    public String getStatusIcon() {
        return (isDone ? "[/]" : "[x]");
    }

    /**
     * Returns a representation of task.
     * @return String that represents the task.
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
