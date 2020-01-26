import java.io.Serializable;

abstract class Task implements Serializable {
    /** Serializable classes must include a serialVersionUID to identify the class. */
    private static final long serialVersionUID = 1561807677731348300L;
    /** Description of the task */
    protected String description;
    /** Status of the task (true represents a completed task, and vice versa) */
    protected boolean isDone; // defaults to false

    public Task(String description) {
        this.description = description;
    }

    private String status() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return String.format("[%s] %s", status(), description);
    }

    public String done() {
        isDone = true;
        return toString();
    }

}