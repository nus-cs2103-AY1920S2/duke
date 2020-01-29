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
    protected static List<Task> taskList = new ArrayList<>(100);

    /**
     * Constructor for task object.
     * @param description How the user describes the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an string icon that suggests if a task is completed.
     * @return String that is either a tick or cross.
     */
    public String getStatusIcon() {
        // return (isDone ? "[\u2713]" : "[\u2718]"); // return tick or X symbols
        return (isDone ? "[✓]" : "[✗]");    // the above did not work.
    }

    /**
     * Returns a representation of task.
     * @return String that represents the task.
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
