package duke.task;

import java.io.Serializable;

// todo: change Task to an Interface and redefine tostring, status, etc. all in child class.
public abstract class Task implements Serializable {
    
    /** Serializable classes must include a serialVersionUID to identify the class. */
    private static final long serialVersionUID = 1561807677731348300L;
    
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

    @Override
    public String toString() {
        return String.format("[%s] %s", status(), description);
    }

    public String done() {
        isDone = true;
        return toString();
    }

}