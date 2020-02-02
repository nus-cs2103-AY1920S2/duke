/**
 * The Task class defines some basic properties
 * and behaviors that every task have. The tasks are set to be not done
 * initially.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task instance with isDone variable set to
     * false.
     *
     * @param description The description or the details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the appropriate icon of
     * the task based on whether the task is done or not.
     *
     * @return The appropriate icon. A / if the task is done, an X, otherwise.
     */
    public String getStatusIcon() {
        return (isDone
                ? "/"
                : "X"); //return tick or X symbols
    }

    /**
     * Marks the task to be done
     * by setting the isDone property of a task to be true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Overrides the Object's toString method
     * and it contains the mark and the description
     * of the task.
     *
     * @return The String that represents the deadline's details.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}