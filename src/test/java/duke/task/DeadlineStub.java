package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;

public class DeadlineStub extends Deadline {
    protected String stringToSaveToDisk;
    protected String statusIcon;
    protected String doneStatusIcon;
    protected String incompleteStatusIcon;
    protected String toString;

    /**
     * Returns a new instance of DeadlineStub.
     *
     * @param description information about deadline
     * @param deadline time of deadline
     * @param isDone deadline completion status
     * @param stringToSaveToDisk used to save Deadline information to save file
     * @param doneStatusIcon icon used for marking deadline as complete
     * @param incompleteStatusIcon icon used for marking deadline as incomplete
     * @param toString String representation of DeadlineStub
     * @throws DateTimeException given deadline is not in required format
     */
    public DeadlineStub(String description, String deadline, boolean isDone,
                        String stringToSaveToDisk, String doneStatusIcon, String incompleteStatusIcon,
                        String toString) throws DateTimeException {
        super(description, deadline, isDone);
        this.stringToSaveToDisk = stringToSaveToDisk;
        this.doneStatusIcon = doneStatusIcon;
        this.incompleteStatusIcon = incompleteStatusIcon;
        this.statusIcon = isDone ? doneStatusIcon : incompleteStatusIcon;
        this.toString = toString;
    }

    @Override
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Returns a String (Unicode Character) based on duke.task.Task completion status.
     *
     * @return String representing Unicode character for check mark or cross
     */
    @Override
    public String getStatusIcon() {
        return statusIcon;
    }

    /**
     * Mark task as done.
     */
    @Override
    public void markAsDone() {
        isDone = true;
        statusIcon = doneStatusIcon;
    }

    /**
     * Mark task as incomplete.
     */
    @Override
    public void markAsIncomplete() {
        isDone = false;
        statusIcon = incompleteStatusIcon;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return toString;
    }

    /**
     * To return a String representation of duke.task.Deadline instance
     *
     * @return String representing task in save file
     */
    @Override
    public String stringToSaveToDisk() {
        return stringToSaveToDisk;
    }

    @Override
    public boolean getTaskCompletionStatus() {
        return isDone;
    }
}
