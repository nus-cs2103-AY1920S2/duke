/**
 * Represents tasks that are added into task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Depending on whether the current status is done or not, it will return tick if done, or X is not done.
     * @return Tick or X symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Depending on whether the current status of the task is done or not. This function's purpose
     * is for saving tasks into a file.
     * @return 1 if done, or 0 if not done.
     */
    public String getStatusInNumber() {
        return isDone? "1" : "0";
    }

    /**
     * The purpose of format function is to set the format for saving task into a file.
     * @return The description of the format, eg. T 0 read.
     */
    public String format() {
        return description;
    }

    /**
     * To print out the task's status and description.
     * @return String of task status.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
