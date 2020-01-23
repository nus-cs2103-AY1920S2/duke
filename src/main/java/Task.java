/**
 * A class that represents Tasks within
 * the bot, to be stored in Storage
 */
public class Task {
    private String taskDetails;
    private boolean doneStatus;

    /**
     * Constructor for a Task
     *
     * @param td String that represents what
     *           was entered to create the task
     */
    public Task(String td) {
        this.taskDetails = td;
        this.doneStatus = false;
    }

    /**
     * Mark this task as done
     */
    public void markAsDone() {
        this.doneStatus = true;
    }

    /**
     * Checks if this Task is completed
     *
     * @return True if Task was previously marked as
     *         done. False if it was never marked
     */
    public boolean isDone() {
        return this.doneStatus;
    }

    @Override
    public String toString() {
        return this.taskDetails;
    }
}
