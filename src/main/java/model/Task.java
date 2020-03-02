package model;

import exceptions.NoDescriptionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

/**
 * An abstract class implements some common methods for task objects.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private String taskTypeString;

    static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final String DEFAULT_TASK_TYPE_STRING = "task";
    private static final boolean DEFAULT_TASK_IS_DONE = false;

    public Task() {

    }

    /**
     * Constructs a {@code Task} with the default task type string.
     * @param description A not empty description.
     * @throws NoDescriptionException If the description is empty.
     */
    public Task(String description) throws NoDescriptionException {
        this(description, DEFAULT_TASK_TYPE_STRING, DEFAULT_TASK_IS_DONE);
    }

    /**
     * Constructs a {@code Task} with the input task type string.
     * @param description A not empty description.
     * @param taskTypeString A not empty task type string.
     * @throws NoDescriptionException If the description is empty.
     */
    public Task(String description, String taskTypeString) throws NoDescriptionException {
        this(description, taskTypeString, DEFAULT_TASK_IS_DONE);
    }

    /**
     * Constructs a {@code Task} with the input description, task type string, and task status.
     * @param description A not empty description.
     * @param taskTypeString A not empty task type string.
     * @param taskIsDone A boolean object indicating whether the task is done.
     * @throws NoDescriptionException If the description is empty.
     */
    public Task(String description, String taskTypeString, boolean taskIsDone) throws NoDescriptionException {
        this.taskTypeString = taskTypeString;
        if ("".equals(description)) {
            throw new NoDescriptionException("OOPS!!! The description of a "
                    + this.taskTypeString
                    + " cannot be empty.\n");
        }
        this.description = description;
        this.isDone = taskIsDone;
    }

    /**
     * Set the description of the task.
     * @param description A not empty description.
     * @throws NoDescriptionException If the description is empty.
     */
    public void setDescription(String description) throws NoDescriptionException {
        if ("".equals(description)) {
            throw new NoDescriptionException("OOPS!!! The description of a "
                    + taskTypeString
                    + " cannot be empty.\n");
        }
        this.description = description;
    }

    /**
     * Checks whether the description of a task contains the inputted keyword.
     * @param keyWord user input keyword.
     * @return true if the description contains the keyword, false otherwise.
     */
    public boolean hasKeyWord(String keyWord) {
        assert description != null : "description not assigned";
        assert description.length() > 0 : "task description is empty";
        return description.toLowerCase().contains(keyWord.toLowerCase());
    }

    public abstract boolean isOnDate(LocalDate targetDate);

    /**
     * An abstract method returning an single-character task type.
     */
    public abstract String getTaskType();

    /**
     * Return the status of whether the task is done.
     * @return tick if the task is done, cross if not done.
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Get the description of the task.
     * @return description of the task.
     */
    public String getDescription() {
        assert description != null : "description not assigned";
        assert description.length() > 0 : "task description is empty";
        return this.description;
    }

    /**
     * Return the details of the task as an array list.
     * @return an arraylist including the description.
     */
    public ArrayList<String> getDetails() {
        assert description != null : "description not assigned";
        assert description.length() > 0 : "task description is empty";
        return new ArrayList<String>(Collections.singletonList(this.description));
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
        assert description != null : "description not assigned";
        assert description.length() > 0 : "task description is empty";
        return "[" + this.getStatusIcon() + "]" + " " + this.getDescription();
    }
}