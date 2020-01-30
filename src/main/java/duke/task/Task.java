package duke.task;

/**
 * Represents the a generic Task that will be extended from.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;

    public enum TaskType { TODO, DEADLINE, EVENT }

    /**
     * Task constructor.
     * @param  name of the task.
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Sets task's status to done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the save-string representation of the task.
     * @return the String representation of the task Storage can save.
     */
    public abstract String toSaveString();

    @Override
    public String toString() {
        return "[" + (isDone ? "✓" : "✘") + "] " + name;
    }
}
