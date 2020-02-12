package model;

import exceptions.NoDescriptionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;

/**
 * An abstract class implements some common methods for task objects.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    private static final String DEFAULT_TASK_TYPE_STRING = "task";

    static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Task() {}

    /**
     * Constructs a {@code Task} with the default task type string.
     * @param description A not empty description.
     * @throws NoDescriptionException If the description is empty.
     */
    public Task(String description) throws NoDescriptionException {
        if ("".equals(description)) {
            throw new NoDescriptionException("OOPS!!! The description of a " +
                    DEFAULT_TASK_TYPE_STRING + " cannot be empty.\n");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task} with the input task type string.
     * @param description A not empty description.
     * @param taskTypeString A not empty task type string.
     * @throws NoDescriptionException If the description is empty.
     */
    public Task(String description, String taskTypeString) throws NoDescriptionException {
        if ("".equals(description)) {
            throw new NoDescriptionException("OOPS!!! The description of a " +
                    taskTypeString + " cannot be empty.\n");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Set the description of the task.
     * @param description A not empty description.
     * @throws NoDescriptionException If the description is empty.
     */
    public void setDescription(String description) throws NoDescriptionException {
        if ("".equals(description)) {
            throw new NoDescriptionException("OOPS!!! The description of a " +
                    DEFAULT_TASK_TYPE_STRING + " cannot be empty.\n");
        }
        this.description = description;
    }

    /**
     * An abstract method returning an single-character task type.
     */
    public abstract String getTaskType();

    /**
     * Return the status of whether the task is done.
     * @return tick if the task is done, cross if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Get the description of the task.
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Return the details of the task as an array list.
     * @return an arraylist including the description.
     */
    public ArrayList<String> getDetails() {
        return new ArrayList<String>(Arrays.asList(this.description));
    }

    /**
     * Return boolean object about whether the task is done.
     * @return boolean object.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Convert task into a string such that the user can view the details from UI.
     * @return String.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.getDescription();
    }
}