package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public abstract String formatSavingName();

    public String getStatusIcon() {
        return (isDone ? "[Y]" : "[N]");
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + this);
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }
}