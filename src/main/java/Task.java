/**
 * A Task object can be a Todo task, a Deadline task or a Event task.
 */
public abstract class Task {
    protected String command;
    protected boolean isDone;

    public Task(String command) {
        this.command = command;
    }

    /**
     * Marks a task as done.
     */
    protected void setDone() {
        isDone = true;
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
        return isDone ? "\u2713" : "\u274C";
    }

    /**
     * Returns the description of a task.
     *
     * @return description of a task.
     */
    protected String getCommand() {
        return command;
    }

    /**
     * Returns whether the task is marked done in int form instead of symbol form, to be displayed in the file
     * saved in hard disk.
     *
     * @return 1 if task is marked done, or 0 if the task is marked undone.
     */
    public int getDoneInt() {
        return isDone() ? 1 : 0;
    }

    public abstract String updateFile();

    public abstract void updateDetails(String details) throws DukeException;
}
