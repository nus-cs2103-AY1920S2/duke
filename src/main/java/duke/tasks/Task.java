package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "N");
    }

    public void makeDone() {
        isDone = true;
    }

    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
