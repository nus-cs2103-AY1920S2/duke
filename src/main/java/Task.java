public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String s) {
        this.description = s;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✗");
    }

    @Override
    public String toString() {
        return '[' + getStatusIcon() + ']' + this.description;
    }
}
