public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        String symbol = isDone ? "\u2713" : "\u2718";
        return "[" + symbol + "] " + description;
    }
}
