package duke.tasks;

import java.time.format.DateTimeFormatter;

/**
 * The class that all the tasks inherit from.
 */
public abstract class Task {

    /**
     * the description for this task.
     */
    private String description;
    /**
     * whether the task has been completed or not.
     */
    private boolean isDone;

    /**
     * creates a new Task.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        assert description != null : "No description for this task";
        this.isDone = false;
    }

    /**
     * returns the status icon.
     *
     * @return Y or N depending on whether isDone is true or false
     */
    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or cross symbols
        return (isDone ? "Y" : "N"); //return Y or N symbols
    }

    /**
     * changes isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * returns output string.
     *
     * @return String to be output to the user
     */
    @Override
    public String toString() {
        assert description != null : "No description for this task";
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * returns file data string.
     *
     * @return String for the file format
     */
    public abstract String fileString();

    /**
     *returns the description of this task.
     *
     * @return the description
     */
    public String getDescription() {
        assert description != null : "No description for this task";
        return this.description;
    }

    /**
     * Updates the timing of a deadline or an event.
     *
     * @param time the new timing
     * @param format the format to be used to parse this
     */
    public abstract void update(String time, DateTimeFormatter format);
}
