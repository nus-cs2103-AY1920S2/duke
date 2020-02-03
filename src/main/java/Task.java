public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "DONE" : "UNDONE");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.description;
    }
}
