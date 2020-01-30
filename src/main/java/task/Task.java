package task;

import java.io.Serializable;

public class Task implements Serializable {
    protected String identifier;
    protected boolean isCompleted;

    public Task(String identifier) {
        this.identifier = identifier;
        this.isCompleted = false;
    }

    public String getStatusIcon() {
        return (isCompleted ? "\u2713" : "\u2717");
    }

    public boolean completeStatus() {
        this.isCompleted = true;
        return true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.identifier;
    }
}
