package duke.task;

public abstract class Task implements Saveable {

    private String description;
    private boolean isDone;

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Mark the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        if (!isDone) {
            return "✗";
        }
        return "✓";
    }

    /**
     * Formats to vertical bar seperated values.
     * @return The string to be used in file saving.
     */
    public String toSaveFormat() {
        return String.format("%d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}