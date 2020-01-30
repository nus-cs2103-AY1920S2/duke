package duke;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2714" : "\u2718");
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getDescriptionWithoutIcon() {
        return this.description;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public String getDescription() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
    public String toString() {
        return "[T]" + getDescription();
    }
}