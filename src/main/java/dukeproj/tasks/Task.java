package dukeproj.tasks;

import dukeproj.enums.TType;

import java.time.LocalDate;

/**
 * Represents an abstract task to be implemented by different type of task subclasses.
 */
public abstract class Task {
    /** The description of the task. */
    protected String task;
    protected boolean isDone;

    public abstract TType getType();

    public abstract LocalDate getDate();

    /**
     * Returns the task.
     *
     * @return task description in String format.
     */
    public String getTask() {
        return task;
    }

    /**
     * Shows whether a task is done or not.
     *
     * @return boolean representing whether the task is done.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Marks a task as done or not done.
     *
     * @param bool boolean to set isDone to.
     */
    public void setDone(boolean bool) {
        isDone = bool;
    }

    /**
     * Constructs a Task object with a description and default isDone = false.
     *
     * @param task description of task.
     */
    public Task(String task) {
        this.task = task;
        isDone = false;
    }

    /**
     * Overloaded constructor that additionally modifies boolean isDone.
     *
     * @param isDone boolean to set isDone to.
     * @param task description of task.
     */
    public Task(boolean isDone, String task) {
        this(task);
        this.isDone = isDone;
    }

}
