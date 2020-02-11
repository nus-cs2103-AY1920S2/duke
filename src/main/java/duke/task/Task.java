package duke.task;

/**
 * Class representation of Task.
 */
public class Task {

    protected String task;
    protected Boolean done = false;

    /**
     * Creates Task object.
     *
     * @param task String describing what to do.
     */
    Task(String task) {
        this.task = task;
    }

    /** Sets this task as done. */
    public void done() {
        this.done = true;
    }

    /**
     * Returns the String representation of the task to print.
     *
     * @return String representation of the task to print.
     */
    @Override
    public String toString() {
        if (done) {
            return "[✓] " + this.task;
        } else {
            return "[✗] " + this.task;
        }
    }

    /**
     * Returns the String representation of the task to save.
     *
     * @return String representation of the task to save.
     */
    public String toSaveString() {
        if (done) {
            return "1";
        } else {
            return "0";
        }
    }
}
