package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatus() {
        return (isDone ? "[O]" : "[X]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return this.getStatus() + " " + this.getDescription();
    }
}