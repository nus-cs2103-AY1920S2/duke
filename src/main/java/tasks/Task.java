package tasks;

/**
 * Task object that forms the base of TodoTask, DeadlineTask and EventTask. Contains methods pertaining to the Task
 * objects.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor of the task. Takes in a description, and sets the task as "not done".
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Completes the task.
     */
    public void complete() {
        isDone = true;
    }

    /**
     * Checks if the task has been completed.
     * @return Boolean that is true if the task is completed.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the description of the task.
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Formats the task to contain a tick or a cross depending on whether it is done or not respectively.
     * @return Task in String form.
     */
    @Override
    public String toString() {
        String marker = "";
        if(isDone) {
            marker = "✓";
        } else {
            marker = "✗";
        }
        return String.format("[%s] %s", marker, description);
    }
}
