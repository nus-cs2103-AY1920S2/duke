public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isDeleted;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isDeleted = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsDeleted() {
        this.isDeleted = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }

}