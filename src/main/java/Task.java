public abstract class Task {
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

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description;
    }
}
