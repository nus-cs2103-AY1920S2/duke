package tasks;

/**
 * Task is the main object used to maintain the list of tasks from the user by Duke.
 * This class provides multiple implementations of Task object.
 */
public class Task {

    /**
     * All Task objects will have the boolean attribute determining if the task has been
     * done, the task details, and the type of Task. Task objects with deadline and event
     * types will have additional local date attributes and additional details when
     * applicable.
     */
    protected boolean isDone;
    protected String taskDetails;

    /**
     * Constructor to construct todo Task object.
     * @param taskDetails Details of task
     */
    public Task(String taskDetails) {
        this.isDone = false;
        this.taskDetails = taskDetails;
    }

    /**
     * Returns tick or cross depending on status of task.
     * @return tick or cross formatted as String.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void undoDone() {
        this.isDone = false;
    }


    /**
     * Gets description of Task object.
     * @return String containing task description.
     */
    public String getDescription() {
        return this.taskDetails;
    }

    /**
     * Sets boolean isDone as true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns details of Task object formatted for storing in hard drive.
     * @return formatted string of task object.
     */
    public String writeToFile() {
        assert !this.taskDetails.isEmpty() : "Task details should not be empty.";
        // Check if Task has been marked as done.
        if (isDone) {
            return " 1 / " + this.taskDetails;
        }
        return " 0 / " + this.taskDetails;
    }
}