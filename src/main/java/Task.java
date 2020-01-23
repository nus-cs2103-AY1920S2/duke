/**
 * A class that represents Tasks within
 * the bot, to be stored in Storage
 */
public abstract class Task {
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

    /**
     * Checks the status of this Task
     *
     * @return A String with a tick character
     *         or a cross character
     */
    private String doneGet() {
        if (this.isDone()) {
            return "[✓]";
        } else {
            return "[✗]";
        }
    }

    /**
     * Gives the type of the current Task
     * as a single letter between square brackets.
     *
     * @return Type of the Task as a String
     */
    public abstract String type();

    @Override
    public String toString() {
        return this.type() + this.taskDetails;
    }
}
