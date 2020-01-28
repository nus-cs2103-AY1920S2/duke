package tasks;

/**
 * Represents a task.
 * Task has a description and can be set to completion.
 *
 * @author ChesterSim
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        String checkbox = this.isDone ? "O" : "X";
//        String checkbox = this.isDone ? "\u2713" : "\u2718";
        return String.format("[%s] %s", checkbox, this.description);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String toSaveFormat();
}
