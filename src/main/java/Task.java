/**
 * Represents a task defined from input by the user to Duke.
 */
public abstract class Task {
    protected String task;
    protected boolean done; //✘

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void setDone() {
        this.done = true; //✓
    }

    /**
     * Returns a corresponding symbol showing the status of the task depending on whether it is done or not.
     * @return String symbol of either a tick or a cross.
     */
    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718");  //return tick or X symbols
    }

    /**
     * Returns a formatted string representing a task to be written into the storage file.
     * Abstract method that is formatted differently by different tasks.
     * @return
     */
    public abstract String toLine();

    /**
     * Returns a formatted string for the task to be printed.
     * @return
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.task;
    }
}
