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
     * Gets the status of this current task and gives the user.
     *
     * @return String status of the current object.
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return Y (done) or N (not done) symbols
    }

    /**
     * Method to be overloaded by the subclasses.
     *
     * @return String Empty String
     */
    public String getDesc() {return ""; }

    /**
     * Method to be overloaded by the subclasses.
     *
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
     *
     * @return String Empty String
     */
    public String getType() { return ""; }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this instanceof Todo && obj instanceof Todo) {
            // No Todo tasks
            // can be the same in the list
            Todo todocmp = (Todo) obj;
            return (this.getDesc()).equals(todocmp.getDesc());
        } else if (this instanceof Event && obj instanceof Event) {
            // Can't attend two events at the same time
            Event eventcmp = (Event) obj;
            return (this.getDate()).equals(eventcmp.getDate());
        } else if (this instanceof Deadline && obj instanceof Deadline) {
            Deadline deadlinecmp = (Deadline) obj;
            return (this.getDesc()).equals(deadlinecmp.getDesc()) ||
                    (this.getDate()).equals(deadlinecmp.getDate());
        }

        return false;
    }
}