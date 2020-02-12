package duke.task;

import java.time.LocalDateTime;

/**
 * Class representation of Task.
 */
public class Task {

    protected String description;
    protected LocalDateTime time = null;
    protected Boolean done = false;

    /**
     * Creates Task object.
     *
     * @param description String describing what to do.
     */
    Task(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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
            return "[✓] " + this.description;
        } else {
            return "[✗] " + this.description;
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
