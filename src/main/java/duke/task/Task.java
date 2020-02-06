package duke.task;

public abstract class Task {
    protected final String description;
    protected final boolean isCompleted;

    protected Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public abstract Task complete();

    @Override
    public String toString() {
        return String.format("[%s] %s", isCompleted ? "\u2713" : "\u2718", description);
    }
}
