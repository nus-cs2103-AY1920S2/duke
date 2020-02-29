/**
 *  This is a Task class.
 *  Stores the Task's description and its isDone status.
 */

package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     * @param desc Content of Task.
     */
    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    /**
     * Returns the description of the Task.
     * @return Description of Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status symbol of the Task.
     * @return Status symbol of Task.
     */
    public String getStatus() {
        return (isDone ? "[O]" : "[X]");
    }

    /**
     * Marks the instance of Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return this.getStatus() + " " + this.getDescription();
    }
}