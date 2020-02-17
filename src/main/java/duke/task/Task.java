package duke.task;

/**
 * Represents a Task in the application.
 * There are 3 types of tasks: Todo, Deadline and Event.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of this task.
     * A tick icon is returned if this task is marked as done. Otherwise, a cross icon is returned.
     *
     * @return The status icon of this task.
     */
    public String getStatusIcon() {
        return isDone ? "\u2714" : "\u2718"; // "return ✔ or ✘ symbols;
    }

    /**
     * Mark this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark this task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the serialized form of this task in the form of a string.
     *
     * @return A string that is the serialized form of this task.
     */
    public abstract String serialize();

    /**
     * Returns true if the description of this task contains the specified keyword.
     * Otherwise, returns false.
     *
     * @param keyword The keyword to check.
     * @return True if the description of this task contains the specified keyword and false otherwise.
     */
    public boolean descriptionContains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns the string representation of this task.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
