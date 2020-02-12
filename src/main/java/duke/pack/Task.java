package duke.pack;

/**
 * Represents a task of the user.
 */
public class Task {
    protected String description;
    protected String fullDesc;
    protected boolean isDone;
    protected String dateToCompare;

    /**
     * Creates a task with the specified description.
     * @param description the task description
     * @param fullDesc the full description of the task
     */
    public Task(String description, String fullDesc, String dateToCompare) {
        this.description = description;
        this.fullDesc = fullDesc;
        this.isDone = false;
        this.dateToCompare = dateToCompare;
    }

    /**
     * Gets the status of task, whether it is done or not.
     * @return A string that shows the corresponding tick/cross symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Gets description of task.
     * @return a string that is the task's description
     */
    public String getFullDesc() {
        return fullDesc; //return task description
    }

    /**
     * Sets the task to done or not done.
     * @param b boolean that indicates whether the task is done
     */
    public void setDone(Boolean b) {
        isDone = b;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    public String getDateToCompare() {
        return dateToCompare;
    }

    public String formatForFile() {
        return description;
    }

    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }
}
