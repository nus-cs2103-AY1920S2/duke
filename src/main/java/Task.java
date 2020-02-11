
/*
 * Task
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 11 February 2020
 *
 */

/**
 * Task class to store the Tasks for duke.
 * @author Daniel Alfred Widjaja
 */

public class Task {
    protected int priority;
    protected String description;
    protected boolean isDone;

    /**
     * Create Task object.
     * @param description The description of the Task.
     * @param isDone The status whether of the Task is done.
     * @param priority The priority the Task in.
     * @param priority The priority of the Task.
     */
    public Task(String description, boolean isDone, int priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    public Task(String description) {
        this(description, false, 10);
    }

    public Task(String description, int priority) {
        this(description, false, priority);
    }

    /**
     * Overrides the default toString method make
     * a custom string.
     * @return Printed format for Task.
     */
    @Override
    public String toString() {
        return ("[T][" + getStatusIcon() + "] " + description);
    }

    /**
     * Provides the status icon.
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Custom string to be saved in database.
     * @return Database formatted string.
     */
    public String getFileString() {
        return "T|" + isDone + "|" + description + "|" + priority;
    }

    /**
     * Makes the status of the object is done.
     * @return the string format of the object.
     */
    public String done() {
        isDone = true;
        return this.toString();
    }

    /**
     * Returns the priority of the task.
     * @return Task Priority.
     */
    public int getPriority() {
        return priority;
    }
}
