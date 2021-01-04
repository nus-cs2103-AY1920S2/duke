import java.time.format.DateTimeParseException;

/**
 * Represents tasks that are added into task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     * @param description Description of deadline task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the current status of task.
     * @return Tick or X symbol.
     */
    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718"; //return tick or X symbols
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the current status of task.
     * @return 1 if done, or 0 if not done.
     */
    public String getStatusInNumber() {
        return isDone ? "1" : "0";
    }

    /**
     * Gets the description of the task.
     * @return The description of the task, eg. read book.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Formats the task details into text file for saving and loading.
     * @return The description of the format, eg. T 0 read.
     */
    public String format() {
        return description;
    }

    /**
     * Prints out the task's status and description.
     * @return String of task status.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
