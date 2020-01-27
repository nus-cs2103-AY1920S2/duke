package main.java;

/**
 * A basic Task class that contains a description and a boolean flag
 * to determine if the task is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class. All tasks are initialised to be not done.
     * @param description Description for the Task class.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to check if the task is completed or not.
     * @return A tick if the task is completed or a cross if the task is not.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Method to return description of the task.
     * @return A string variable that is the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Method to mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Print the Task description, status icon.
     * @return A string that contains the description and the status icon of the task.
     */
    @Override
    public String toString() {
        return  description + " " + getStatusIcon();
    }
}
