package tasks;

/**
 * Represents a task that needs to be completed.
 * It contains a description of the task,
 * and a boolean isDone that states if the task is completed or not.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //return (isDone ? "\u2713": "\u2718"); //return tick or X symbols
        return (isDone ? "[✓]" : "[✗]");
    }

    public int getStatusNum() {
        return (isDone ? 1 : 0);
    }

    public String getTaskType() {
        return "";
    }

    public boolean contains(String keyword) {
        if (this.description.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }

    public void setStatus(String status) {
        this.isDone = (status.equals("0") ? false : true);
    }

    public String getDescription() {
        return this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

    public String saveString() {
        return getStatusNum() + " | " + getDescription();
    }
}
