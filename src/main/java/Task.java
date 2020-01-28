public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String d) {
        this.description = d;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String generateWriteFormat() {
        return null;
    }
}
