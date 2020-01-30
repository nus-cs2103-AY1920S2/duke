package duke.util;

/**
 * Task
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class Task {
    protected String description;
    public boolean isDone;

    /**
     * Constructs a Task instance.
     * @param description The description or details of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the Task.
     * @return The description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status icon of the Task.
     * @return The status icon of the Task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Overrides the Object's toString method
     * and contains the status icon and description of the task.
     * @return The String that containing the Task's details.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
