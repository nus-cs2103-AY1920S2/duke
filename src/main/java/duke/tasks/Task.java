package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object
     * @param description Information about the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of a tick if task is done, else a cross
     * @return A string representation of a tick if task is done, else a cross
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the description of the task
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks this task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Checks if this task is done
     * @return true if task is done, else false
     */
    public boolean isDone() { return isDone; }

    /**
     * Returns a string representation of this task's description
     * @return A a string representation of this task's description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    //...
}