package duke.task;

import java.io.Serializable;

public abstract class Task implements Serializable {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor of the Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the isDone flag of the task.
     *
     * @param isDone Whether the task should be marked as done or not done.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    protected String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]"; //return [tick] or [X] symbols
    }

    protected abstract String getTypeIcon();

    protected abstract TaskType getTaskType();

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description;
    }

    public enum TaskType {
        TASK_TYPE_TODO,
        TASK_TYPE_EVENT,
        TASK_TYPE_DEADLINE
    }
}
