/**
 * Represents a task with a description String and an isDone boolean
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object that stores a description String and an isDone boolean
     * @param description a description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }
    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        String symbol = isDone ? "\u2713" : "\u2718";
        return "[" + symbol + "] " + description;
    }
}
