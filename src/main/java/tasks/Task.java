package tasks;

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
