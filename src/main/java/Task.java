public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public int getIsTaskDone() {
        return (isDone ? 1 : 0);
    }

    public String getDescription() {
        return this.description;
    }

    public String markAsDone() {
        this.isDone = true;
        return this.getStatusIcon();
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }
}
