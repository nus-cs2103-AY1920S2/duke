/**
 * A class that represents Tasks within
 * the bot, to be stored in Storage
 */
public abstract class Task {
    private String taskDetails;
    private String taskTime;
    private boolean doneStatus;

    /**
     * Constructor for a Task
     *
     * @param td String that represents what
     *           was entered to create the Task
     *
     * @param tt String that represents a time
     *           associated with the Task
     */
    public Task(String td, String tt) {
        this.taskDetails = td;
        this.taskTime = tt;
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
     * as a single letter
     *
     * @return Type of the Task as a String
     */
    public abstract String type();

    @Override
    public String toString() {
        return "[" + this.type() + "]" +
            this.doneGet() + " " + this.taskDetails +
            " " + this.taskTime;
    }
}
