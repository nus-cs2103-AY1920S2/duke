package model;

import exceptions.NoDescriptionException;

import java.time.LocalDate;

/**
 * Represents an todo task in the inner-task list.
 */
public class ToDoTask extends Task {
    public static final String TASK_TYPE_CHA = "T";

    private static final String TASK_TYPE_STRING = "todo task";

    public ToDoTask() {

    }

    /**
     * Constructs an {@code ToDoTask}.
     * @param description A not empty description.
     * @throws NoDescriptionException if the description is empty.
     */
    public ToDoTask(String description) throws NoDescriptionException {
        super(description, TASK_TYPE_STRING);
    }

    /**
     * Constructs an {@code ToDoTask} with input description and isDone status.
     * @param description A not empty description.
     * @param isDone A boolean indicating whether the task is done.
     * @throws NoDescriptionException if the description is empty.
     */
    public ToDoTask(String description, boolean isDone) throws NoDescriptionException {
        super(description, TASK_TYPE_STRING, isDone);
    }

    /**
     * Convert the details of the task, such as description, deadline to one arraylist
     * for encoder to encode in a string.
     * @return ArrayList object
     */
    @Override
    public String getTaskType() {
        return TASK_TYPE_CHA;
    }

    @Override
    public boolean isOnDate(LocalDate targetDate) {
        return false;
    }

    /**
     * Convert task into a string such that the user can view the details from UI.
     * @return String.
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE_CHA + "]"  + super.toString();
    }
}