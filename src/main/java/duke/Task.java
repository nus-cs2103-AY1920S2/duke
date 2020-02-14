package duke;

public abstract class Task {
    protected String description;

    public boolean isDone() {
        return isDone;
    }

    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    abstract String toDatabaseString();
}
