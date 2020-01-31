package duke;

public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public void markAsDone() {
        isDone = true;
    }

    public boolean getDone() {
        return isDone;
    }

    public abstract String getMnemonic();

    @Override
    public String toString() {
        return ("[" + (isDone ? "\u2713" : "\u2718") + "] " + name);
    }
}
