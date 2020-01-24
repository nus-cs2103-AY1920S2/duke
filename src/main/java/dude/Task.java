package dude;

public class Task {
    private final String details;
    private boolean isDone;

    public Task(String details, boolean isDone) {
        this.details = details;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), details);
    }

    private String getStatusIcon() {
        return (isDone ? "O" : "X"); 
    }
}