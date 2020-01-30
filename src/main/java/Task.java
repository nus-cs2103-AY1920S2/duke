public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        String icon = (isDone ? "\u2713" : "\u2718");
        return "["+ icon + "]" + " " + this.description;
    }

    public String toFileString() {
        int status = (isDone? 1 : 0);
        return " | " + status + " | " + this.description;
    }
}