public abstract class Task {
    protected String name;
    protected boolean isDone;

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void MarkAsDone() {
        isDone = true;
    }

    public abstract String toSaveString();

    @Override
    public String toString() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] " + name;
    }
}
