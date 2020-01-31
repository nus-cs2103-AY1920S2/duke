package duke.tasks;

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
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * returns the status icon.
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
     * @return String to be output to the user
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * returns file data string.
     * @return String for the file format
     */
    public abstract String fileString();

    /**
     *returns the description of this task.
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }
}
