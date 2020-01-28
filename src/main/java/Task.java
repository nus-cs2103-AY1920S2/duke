/**
 * Task Class.
 * The parent class of all task classes (Event, Deadline, Todo)
 *
 * @author Amos Cheong
 */
public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of this current task and gives the user
     * @return String status of the current object.
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return Y (done) or N (not done) symbols
    }

    /**
     * Method to be overloaded by the subclasses.
     * @return String Empty String
     */
    public String getDesc() {return ""; }

    /**
     * Method to be overloaded by the subclasses.
     * @return String Empty String.
     */
    public String getDate() {return ""; }

    /**
     * Set the current object to be done. Used by its subclasses.
     */
    public void taskIsDone() {
        this.isDone = true;
    }

    /**
     * Method to be overloaded by the subclasses.
     * @return String Empty String
     */
    public String getType() { return ""; }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}