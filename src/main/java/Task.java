public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
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

    @Override
    public String toString() {
        return ("[" + (isDone ? "\u2713" : "\u2718") + "] " + name);
    }
}
