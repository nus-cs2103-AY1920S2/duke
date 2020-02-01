package duke.task;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/** Class defining the minimal methods required to be implemented by a Task entity */
public abstract class Task implements Serializable {
    
    /** Serializable classes must include a serialVersionUID to identify the class. */
    private static final long serialVersionUID = 1561807677731348300L;
    protected final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
    
    /** Description of the task */
    protected String description;
    
    /** Status of the task (true represents a completed task, and vice versa) */
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    private String status() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /** toString implementation adding a Status marker representing the task */
    @Override
    public String toString() {
        return String.format("[%s] %s", status(), description);
    }

    /**
     * Sets the status of this task to 'Done'
     * @return a string representing the new state of the task
     */
    public String done() {
        isDone = true;
        return toString();
    }

}