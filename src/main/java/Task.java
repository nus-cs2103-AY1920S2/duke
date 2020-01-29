/**
 * Task object to describe a task to be stored in task list.
 */
public class Task {

    private String description;
    protected boolean completed;
    protected int completedCode;

    /**
     * Creates a task object.
     * @param description description of the task.
     */
    public Task(String description) {

        this.description = description;
        this.completed = false;
        this.completedCode = 0;

    }

    /**
     * Changes the status of completed of the task, if method is invoked, completed will be turn to true.
     */
    public void completedTask() {

        this.completed = true;
        this.completedCode = 1;
    }

    /**
     * Returns the description of the task.
     * @return the description of the task.
     */
    public String getDescription() {

        return this.description;
    }

    /**
     * Parent method for the child to implement.
     * @return dummy return.
     */
    public String saveToHardDiskFormat() {

        return "";
    }

    /**
     * Overrided toString method to show whether a task is completed or not with the tick and cross symbols.
     * @return tick or cross with the description of the task.
     */
    @Override
    public String toString() {

        if (this.completed) {
            return ("[\u2713] " + this.description);
        } else {
            return ("[\u2718] " + this.description);
        }

    }

}
