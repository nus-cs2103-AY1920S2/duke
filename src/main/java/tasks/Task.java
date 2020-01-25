package tasks;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void complete() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String marker = "";
        if(isDone) {
            marker = "✓";
        } else {
            marker = "✗";
        }

        return String.format("[%s] %s", marker, description);
    }
}
