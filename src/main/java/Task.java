/**
 * Class representation of Task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task Constructor
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Task's getStatusIcon method
     * @return tick or cross dependent on isDone boolean attribute
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        //return (isDone? "[Y]" : "[N]");
    }

    /**
     * Task's markAsDone method
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Task's overriden toString method
     * @return String representation of Tasks
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}