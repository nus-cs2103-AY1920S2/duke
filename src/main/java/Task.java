/**
 * Represents a Task object
 * Parent class of Todo, Event and Deadline classes
 */
public class Task {

    /** Describes the task*/
    protected String description;
    /** Whether the task is done or not*/
    protected String status = "Not Done";

    /**
     * Creates a task object
     *
     * @param description describes the task being created
     */
    public Task (String description) {
        this.description = description;
    }

    /**
     * Changes the status of the task as "Done"
     * Prints out a message to user that task is done
     */
    public void markAsDone() {
        this.status = "Done";
    }

    /**
     * Lets user know whether task is done or not
     *
     * @return whether the task is "Done" or "Not Done"
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Formats the Task object into a string so it can be saved into hard disk
     *
     * @return a formatted string representing the task object
     */
    public String saveFile() {
        if (this.status.equals ("Done")) {
            return  "1|" + this.description;
        } else {
            return  "0|" + this.description;
        }
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Converts the task object into a string that describes the task
     *
     * @return a string that describes the task and whether is has been done
     */
    @Override
    public String toString() {
        return "[" + this.status + "] " + this.description;
    }
}