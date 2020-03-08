package duke.tasks;

/**
 * Represents a task in Duke.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; //default not done
    }

    /**
     * Returns the status of the task based on boolean isDone.
     * @return tick if true, cross if false.
     */
    public String getStatus() {
        return (isDone ? "✓" : "✗");
    }

    /**
     * Marks the task as done if completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }

    /**
     * Returns task in the format to be saved in file.
     * @return task as a string.
     */
    public String formatText() {
        return "X | " + (this.isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns whether task description contains the keyword.
     * @param key Keyword.
     * @return true if contains, false if not.
     */
    public boolean contains(String key) {
        return this.description.contains(key);
    }
}

