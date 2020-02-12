package model;

import exceptions.NoDescriptionException;

/**
 * Represents an todo task in the inner-task list.
 */
public class ToDoTask extends Task {
    private static final String TASK_TYPE_STRING = "todo task";
    private static final String TASK_TYPE_CHA = "T";

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
     * Convert the details of the task, such as description, deadline to one arraylist
     * for encoder to encode in a string.
     * @return ArrayList object
     */
    @Override
    public String getTaskType() {
        return TASK_TYPE_CHA;
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