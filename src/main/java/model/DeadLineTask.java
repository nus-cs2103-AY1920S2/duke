package model;

import exceptions.NoDescriptionException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Presents the deadline task in the task list.
 */
public class DeadLineTask extends Task {
    public static final String TASK_TYPE_CHA = "D";

    private static final String TASK_TYPE_STRING = "deadline task";

    private LocalDateTime by;

    public DeadLineTask() {

    }

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
     * Constructs an {@code DeadLineTask}.
     * @param description a not empty description.
     * @param by a valid date time object.
     * @param isDone A boolean indicating whether the task is done.
     * @throws NoDescriptionException if the description is empty.
     */
    public DeadLineTask(String description, LocalDateTime by, boolean isDone) throws NoDescriptionException {
        super(description, TASK_TYPE_STRING, isDone);
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
        assert description != null: "task description not assigned";
        assert description.length() > 0: "task description is empty";

        return new ArrayList<String>(Arrays.asList(
                this.description,
                this.by.format(DATE_TIME_FORMAT)));
    }

    @Override
    public boolean isOnDate(LocalDate targetDate) {
        assert by != null: "the value of 'by' not assigned";
        return by.toLocalDate().equals(targetDate);
    }

    /**
     * Convert task into a string such that the user can view the details from UI.
     * @return String.
     */
    @Override
    public String toString() {
        assert by != null: "the value of 'by' not assigned";
        return "[" + TASK_TYPE_CHA + "]" + super.toString() + " (by: " + by.format(DATE_TIME_FORMAT) + ")";
    }
}
