package bot.task;

import bot.util.PrettyTime;

/**
 * A class that represents Tasks within
 * the bot, to be stored in Storage
 */
public abstract class Task {
    private String taskDetails;
    private PrettyTime taskTime;
    private boolean doneStatus;

    /**
     * Constructor for a Task
     *
     * @param td String that represents what
     *           was entered to create the Task
     *
     * @param tt PrettyTime that represents a time
     *           associated with the Task
     */
    public Task(String td, PrettyTime tt) {
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
     * Gets the details of the Task, usually a
     * short description
     *
     * @return String containing the details
     */
    public String getTaskDetails() {
        return this.taskDetails;
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

    /**
     * Gives an appropriate verb to use before
     * the time of the Task is displayed
     *
     * @param rawTime The original String
     *                representing Task time
     * @return String representing time with
     * the appropriate formatting for the
     * type of Task
     */
    public abstract String timeVerb(String rawTime);

    /**
     * Gives the time of the current Task
     *
     * @return PrettyTime representing time
     * of the Task
     */
    public PrettyTime getPrettyTime() {
        return this.taskTime;
    }

    @Override
    public String toString() {
        return "[" + this.type() + "]"
            + this.doneGet() + " " + this.taskDetails + " "
            + this.timeVerb(this.taskTime.toString());
    }
}
