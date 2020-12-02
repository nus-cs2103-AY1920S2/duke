package duke.task;

/**
 * Represents tasks without any date/time attached to it e.g. visit new theme park.
 */
public class Todo implements Task {
    protected String description;
    protected boolean isDone;

    public Todo(String description) {
        this(description, false);
    }

    /**
     * Returns a new Todo instance.
     *
     * @param description information about Todo
     * @param isDone completion status of Todo
     */
    public Todo(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Mark task as done.
     */
    @Override
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark task as incomplete.
     */
    @Override
    public void markAsIncomplete() {
        this.isDone = false;
    }

    /**
     * Returns a String (Unicode Character) based on duke.task.Task completion status.
     *
     * @return String representing Unicode character for check mark or cross
     */
    @Override
    public String getStatusIcon() {
        if (isDone) {
            return "\u2713"; // Check mark symbol
        } else {
            return "\u2718"; // Cross mark symbol
        }
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        // e.g. format: [T][✗] borrow book
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }

    /**
     * To return a String representation of duke.task.Todo instance
     *
     * @return String representing task in save file
     */
    @Override
    public String stringToSaveToDisk() {
        // e.g. format: todo,1,read book
        return String.format("todo,%s,%s", isDone ? 1 : 0, description);
    }

    @Override
    public boolean getTaskCompletionStatus() {
        return isDone;
    }
}
