package duke.pack;

/**
 * represents a task of the user
 */
public class Task {
    protected String description;
    protected String fullDesc;
    protected boolean isDone;

    /**
     * creates a task with the specified description
     * @param description the task description
     * @param fullDesc the full description of the task
     */
    public Task(String description, String fullDesc) {
        this.description = description;
        this.fullDesc = fullDesc;
        this.isDone = false;
    }

    /**
     * gets the status of task, whether it is done or not
     * @return A string that shows the corresponding tick/cross symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * gets description of task
     * @return a string that is the task's description
     */
    public String getFullDesc() {
        return fullDesc; //return task description
    }

    /**
     * sets the task to done or not done
     * @param b boolean that indicates whether the task is done
     */
    public void setDone(Boolean b) {
        isDone = b;
    }

    /**
     * marks the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    public String formatForFile() {
        return description;
    }

    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }
}
