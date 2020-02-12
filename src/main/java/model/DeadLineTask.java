package model;

import exceptions.NoDescriptionException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Presents the deadline task in the task list.
 */
public class DeadLineTask extends Task {
    static final String TASK_TYPE_STRING = "deadline task";
    static final String TASK_TYPE_CHA = "D";

    protected LocalDateTime by;

    public DeadLineTask() {}

    /**
     * Constructs an {@code DeadLineTask}.
     * @param description a not empty description.
     * @param by a valid date time object.
     * @throws NoDescriptionException if the description is empty.
     */
    public DeadLineTask(String description, LocalDateTime by) throws NoDescriptionException {
        super(description, TASK_TYPE_STRING);
        this.by = by;
    }

    /**
     * get and return the single-character task type. E.g. "E", "T", or "D".
     * @return TASK_TYPE_CHA single-character task type.
     */
    @Override
    public String getTaskType() {
        return TASK_TYPE_CHA;
    }

    /**
     * Convert the details of the task, such as description, deadline to one arraylist.
     * for encoder to encode in a string.
     * @return ArrayList object
     */
    @Override
    public ArrayList<String> getDetails() {
        return new ArrayList<String>(Arrays.asList(
                this.description,
                this.by.format(DATE_TIME_FORMAT)));
    }

    /**
     * Convert task into a string such that the user can view the details from UI.
     * @return String.
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE_CHA + "]" + super.toString() + " (by: " + by.format(DATE_TIME_FORMAT) + ")";
    }
}
