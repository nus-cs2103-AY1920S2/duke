package model;

import exceptions.NoDescriptionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;


/**
 * Represents an event task in the inner-task list.
 */
public class EventTask extends Task{
    static final String TASK_TYPE_STRING = "event task";
    static final String TASK_TYPE_CHA = "E";

    protected LocalDateTime at;

    public EventTask() {}

    /**
     * Constructs an {@code EventTask}.
     * @param description A not empty description.
     * @param at A valid datetime object.
     * @throws NoDescriptionException if the description is empty.
     */
    public EventTask(String description, LocalDateTime at) throws NoDescriptionException {
        super(description, TASK_TYPE_STRING);
        this.at = at;
    }

    /**
     * get and return the single-character task type. E.g. "E", "T", or "D".
     * @return TASK_TYPE_CHA single-character task type.
     */
    @Override
    public ArrayList<String> getDetails() {
        return new ArrayList<String>(Arrays.asList(
                this.description,
                this.at.format(DATE_TIME_FORMAT)));
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
        return "[" + TASK_TYPE_CHA + "]"  + super.toString() + " (at: " + at.format(DATE_TIME_FORMAT) + ")";
    }
}
