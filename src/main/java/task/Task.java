package task;

import java.io.Serializable;

public abstract class Task implements Serializable {

    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

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
