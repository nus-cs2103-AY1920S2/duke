package dukeclasses;

/**
 * This class is the parent class of deadline,todo and event.
 */
public class Task{

    protected String description;
    protected boolean isDone;

    public Task(String s) {
        this.description = s;
        this.isDone = false;
    }

    /**
     * returns a string.
     *
     * @return returns a string containing the details
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon() {

        return isDone? "\u2713" : "\u2718";
    }

    /**
     * Used to change the isDone boolean of a dukeClasses.Task.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * gets the isDone boolean for a task.
     */
    public boolean getIsDone() {

        return this.isDone;
    }
}
