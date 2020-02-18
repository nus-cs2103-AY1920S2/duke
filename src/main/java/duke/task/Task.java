package duke.task;

/**
 * Represents a task in the todo list. It has task description and can be done or not done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task instance, initialized as undone.
     * @param description The description of the task details.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of the task in the data file.
     * @return string representation of the task in the data file.
     */
    public String toStringInFile() {
        return toString();
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }


}