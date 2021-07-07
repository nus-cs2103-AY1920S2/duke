package duke.pack;

/**
 * Represents a task of the user.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String dateToCompare;
    protected String timeToCompare;

    /**
     * Creates a task with the specified description.
     * @param description the task description
     * @param dateToCompare date used in comparator
     * @param timeToCompare time used in comparator
     */
    public Task(String description, String dateToCompare, String timeToCompare) {
        this.description = description;
        this.isDone = false;
        this.dateToCompare = dateToCompare;
        this.timeToCompare = timeToCompare;
    }

    /**
     * Gets the status of task, whether it is done or not.
     * @return A string that shows the corresponding tick/cross symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
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

    public String getTimeToCompare() {
        return timeToCompare;
    }

    public String formatForFile() {
        return description;
    }

    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }
}
