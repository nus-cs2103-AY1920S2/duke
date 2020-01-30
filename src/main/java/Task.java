public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public String getDescription() {
        return description;
    }

    public String markAsDone() {
        isDone = true;
        return getStatusIcon();
    }
@Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
