public abstract class Task {
    protected String name;
    private boolean isDone;

    public Task (String name) {
        this.name = name;
        this.isDone = false;
    }

    protected Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String toString() {
        String done = isDone? "\u2713" : "\u2718";
        return "[" + done + "] " + name;
    }

    public abstract Task setDone();
}