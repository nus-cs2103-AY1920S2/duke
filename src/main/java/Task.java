/**
 * The representation of task in the project.
 */
public class Task {

    /**
     * The total count of existing tasks.
     */
    protected static int totalTaskCount = 0;
    /**
     * The description of the task.
     */
    protected String description;
    /**
     * The completion of the task.
     */
    protected boolean isDone;

    /**
     * Constructs a new instance of Task.
     *
     * @param description the description of the task.
     */
    public Task(String description) {

        this.description = description;
        isDone = false;
        totalTaskCount++;
    }

    /**
     * Gets the total existing task count.
     *
     * @return the total task count.
     */
    public static int getTotalTaskCount() {

        return totalTaskCount;
    }

    /**
     * Sets the total task count.
     *
     * @param totalTaskCount the new total task count.
     * @throws InvalidArgumentAelitaException if totalTaskCount &lt; 0.
     */
    public static void setTotalTaskCount(int totalTaskCount) throws InvalidArgumentAelitaException {

        if (totalTaskCount < 0) {
            throw new InvalidArgumentAelitaException();
        }

        Task.totalTaskCount = totalTaskCount;
    }

    /**
     * Gets the description of the task.
     *
     * @return the description.
     */
    public String getDescription() {

        return description;
    }

    /**
     * Gets the status icon.
     *
     * @return the status icon.
     */
    public String getStatusIcon() {

        return (isDone ? "\u2713" : "\u2718");

    }

    /**
     * Marks the task as done.
     *
     * @throws DuplicateMarkAelitaException if task has already been marked as done.
     */
    public void markAsDone() throws DuplicateMarkAelitaException {

        if (isDone) {
            throw new DuplicateMarkAelitaException();
        }
        isDone = true;
    }

    @Override
    public String toString() {

        return "[" + getStatusIcon() + "] " + description;
    }

}
