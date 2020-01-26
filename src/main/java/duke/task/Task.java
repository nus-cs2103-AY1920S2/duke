package duke.task;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Task constructor.
     * @param  name of the task.
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Sets task's status to done.
     */
    public void MarkAsDone() {
        isDone = true;
    }

    /**
     * Gets the save-string representation of the task.
     * @return the String representation of the task Storage can save.
     */
    public abstract String toSaveString();

    @Override
    public String toString() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] " + name;
    }
}
