public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "2713" : "2718"); //return tick or X symbols
    }

    public void markDone() {
        this.isDone = true;
    }
}