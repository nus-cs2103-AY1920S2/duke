package tasks;

/**
 * Represents a task.
 * Task has a description and can be set to completion.
 */
public abstract class Task {
    /** Description of task. */
    private String description;
    /** Status of task. */
    private boolean isDone;

    /**
     * Creates a task layer with description and a not done status.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Converts task into format used in save file.
     *
     * @return Save format of task in a string for easier back-conversion.
     */
    public abstract String toSaveFormat();

    @Override
    public String toString() {
        String checkbox = this.isDone ? "O" : "X";
//        String checkbox = this.isDone ? "\u2713" : "\u2718";
        return String.format("[%s] %s", checkbox, this.description);
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets status of completion of task.
     * @return Status of task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Gets description of task.
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }
}
