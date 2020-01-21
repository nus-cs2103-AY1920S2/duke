public class Task {
    private String description = "";
    private boolean isDone = false;
    
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String toString() {
        return String.format("[%s] %s", (isDone ? "\u2713" : "\u2718"), description);
    }

}
