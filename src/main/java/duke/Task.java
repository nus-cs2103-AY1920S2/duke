package duke;

public class Task {
    private boolean isDone;
    private String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Set task to be done.
     */
    public void doTask() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "✓" : "✗", this.name);
    }
}
