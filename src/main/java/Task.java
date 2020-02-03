import static java.lang.Boolean.TRUE;

/**
 * A Task object can be a Todo task, a Deadline task or a Event task.
 */
public class Task {
    protected String command;
    protected boolean isDone;

    public Task(String command) {
        this.command = command;
    }

    /**
     * Marks a task as done.
     */
    protected void setDone() {
        isDone = TRUE;
    }

    /**
     * Returns whether a task has been marked as done.
     *
     * @return whether a task is marked as done.
     */
    protected boolean isDone() {
        return isDone;
    }

    /**
     * Returns the symbol to show whether a task has been marked as done.
     *
     * @return ✓ if task is done, or ✗ if task is not done.
     */
    protected String getDoneSymbol() {
        return isDone ? "✓" : "✗";
    }

    /**
     * Returns the description of a task.
     *
     * @return description of a task.
     */
    protected String getCommand() {
        return command;
    }

    protected String updateFile() {
        return null;
    }
}
