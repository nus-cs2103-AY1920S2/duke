public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this(description, false);
    }

    @Override
    public String toString() {
        return ("[T][" + getStatusIcon() + "] " + description);
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getFileString() {
        return "T|" + isDone + "|" + description;
    }

    public String done() {
        isDone = true;
        return this.toString();
    }
}
