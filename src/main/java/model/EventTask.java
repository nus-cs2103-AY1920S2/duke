package model;

import exceptions.NoDescriptionException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents an event task in the inner-task list.
 */
public class EventTask extends Task {
    public static final String TASK_TYPE_CHA = "E";

    private static final String TASK_TYPE_STRING = "event task";

    private LocalDateTime at;

    public EventTask() {

    }

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
     * Constructs an {@code EventTask} with description, dateTime, and isDone status.
     * @param description A not empty description.
     * @param at A valid datetime object.
     * @param isDone A boolean indicating whether the task is done.
     * @throws NoDescriptionException if the description is empty.
     */
    public EventTask(String description, LocalDateTime at, boolean isDone) throws NoDescriptionException {
        super(description, TASK_TYPE_STRING, isDone);
        this.at = at;
    }

    /**
     * get and return the single-character task type. E.g. "E", "T", or "D".
     * @return TASK_TYPE_CHA single-character task type.
     */
    @Override
    public ArrayList<String> getDetails() {
        assert description != null: "task description not assigned";
        assert description.length() > 0: "task description is empty";

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

    @Override
    public boolean isOnDate(LocalDate targetDate) {
        assert at != null: "the value of 'at' not assigned";
        return at.toLocalDate().equals(targetDate);
    }

    /**
     * Convert task into a string such that the user can view the details from UI.
     * @return String.
     */
    @Override
    public String toString() {
        assert at != null: "the value of 'at' not assigned";
        return "[" + TASK_TYPE_CHA + "]"  + super.toString() + " (at: " + at.format(DATE_TIME_FORMAT) + ")";
    }
}
