public class Task {
    private String description;
    private boolean isDone;
    
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return String.format("[%s] %s", (isDone ? "\u2713" : "\u2718"), description);
    }

    public void markDone() {
        isDone = true;
    }
}
