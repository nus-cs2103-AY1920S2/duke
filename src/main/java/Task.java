public class Task {
    protected String description;
    protected boolean isDone; // defaults to false

    public Task(String description) {
        this.description = description;
    }

    private String status() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return String.format("[%s] %s", status(), description);
    }

    public String done() {
        isDone = true;
        return toString();
    }

}