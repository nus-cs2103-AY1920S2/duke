public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class
     * @param description String containing the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter for the status icons, depending on whether the task has been completed:
     * "Y" is returned if the task is completed
     * "N" is returned if the task is incomplete
     * @return String for the status icons
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); // as ticks and X don't parse well on powershell
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Sets the status of this task to done (Default: not done)
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}