/**
 * The Task object contains parameters and information which defines the Task, such as the name, time, and whether it
 * is done. This object also handles any editing to the contents of the Task.
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    private char taskType;
    private boolean isDone;
    private String taskName;
    private LocalDateTime taskTime;

    /**
     * Constructor for a new generic Task object.
     * @param taskType defines the type of this Task.
     * @param isDone whether the Task is done.
     * @param taskName the name of the Task.
     * @param taskTime the end time of the Task.
     */
    public Task(char taskType, boolean isDone, String taskName, String taskTime) {
        this.taskType = taskType;
        this.isDone = isDone;
        this.taskName = taskName;
        this.taskTime = taskTime.isEmpty()
                ? LocalDateTime.MIN
                : this.parseDate(taskTime);
    }

    /**
     * Parses the date and time in a string passed into the constructor.
     * @param taskTime the end time of this Task.
     * @return a LocalDateTime object containing a string representation of a time.
     *         If no time is present, a default time "2359" is used.
     * @throws DateTimeParseException if the string is not in d/M/yyyy HHmm format.
     */
    public LocalDateTime parseDate(String taskTime) throws DateTimeParseException {
        return LocalDateTime.parse(taskTime, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Returns if this Task is done.
     * @return the status of the Task.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns if this Task is done, in a unicode format.
     * @return the status of the Task
     */
    public String getStatusUnicode() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns the name of this Task.
     * @return the name of the Task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the type of this Task.
     * @return the type of the Task.
     */
    public char getTaskType() {
        return this.taskType;
    }

    /**
     * Returns the end time of this Task, in a String format.
     * @return the end time of the Task.
     */
    public String getTaskTime() {
        return this.taskTime.getDayOfMonth() + "/" + this.taskTime.getMonthValue() + "/" + this.taskTime.getYear()
                + " " + this.taskTime.getHour()
                + (this.taskTime.getMinute() > 9 ? this.taskTime.getMinute() : "0" + this.taskTime.getMinute());
    }

    /**
     * Marks this Task as done, if it has not already been marked as done.
     * @return a String message indicating if the Task has been marked done.
     */
    public String markDone() {
        if (this.isDone) {
            return "Oops! This task was already marked as done!";
        }
        this.isDone = true;
        return "Marked this task as done:\n    " + this;
    }

    /**
     * An overriden toString() function to display a custom format when this Task is called as a String.
     * @return a String representing this Task.
     */
    @Override
    public String toString() {
        if (this.taskType == 'T') {
            return "[" + taskType + "] " + "[" + getStatusUnicode() + "] " + this.taskName;

        } else if (this.taskType == 'D') {
            return "[" + taskType + "] " + "[" + getStatusUnicode() + "] " + this.taskName + " (by: " + this.getTaskTime() + ")";

        } else if (this.taskType == 'E') {
            return "[" + taskType + "] " + "[" + getStatusUnicode() + "] " + this.taskName + " (at: " + this.getTaskTime() + ")";
        } else {
            return "";
        }
    }
}
