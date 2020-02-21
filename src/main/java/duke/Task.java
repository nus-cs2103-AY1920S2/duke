/**
 * The Task object contains parameters and information which defines the Task, such as the name, time, and whether it
 * is done. This object also handles any editing to the contents of the Task.
 */

package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

abstract class Task {
    private char taskType;
    private boolean isDone;
    private String taskName;
    private LocalDate taskDate;
    private LocalTime taskTime;

    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
    static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Constructor for a new generic Task object.
     * @param taskType defines the type of this Task.
     * @param isDone whether the Task is done.
     * @param taskName the name of the Task.
     * @param taskDateTime the given date and/or time of the Task.
     * @throws DateTimeParseException if the date or time provided is in the wrong format.
     */
    public Task(char taskType, boolean isDone, String taskName, String taskDateTime) throws DateTimeParseException {
        this.taskType = taskType;
        this.isDone = isDone;
        this.taskName = taskName;
        this.taskDate = LocalDate.EPOCH;
        this.taskTime = LocalTime.MAX;

        parseDateTime(taskDateTime);

    }

    /**
     * Parses the date and time given and stores it in the object.
     * @param taskDateTime the given date and/or time of the Task.
     * @throws DateTimeParseException if the date or time provided is in the wrong format.
     */
    public void parseDateTime(String taskDateTime) throws DateTimeParseException {
        if (!taskDateTime.isEmpty()) {
            String[] dateTime = taskDateTime.split(" ");
            this.taskDate = LocalDate.parse(dateTime[0], DATE_FORMATTER);
            if (dateTime.length > 1) {
                this.taskTime = LocalTime.parse(dateTime[1], TIME_FORMATTER);
            }
        }
    }

    /**
     * Returns if this Task is done.
     * @return the status of the Task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns if this Task is done, in a unicode format.
     * @return the status of the Task
     */
    public String getDoneStatusUnicode() {
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
     * Returns the date and time of this Task in a string format.
     * @return the Date and Time of this Task.
     */
    public String getTaskDateTime() {
        return this.getTaskDate().format(DATE_FORMATTER) + " " + this.getTaskTime().format(TIME_FORMATTER);
    }

    /**
     * Returns the end date of this Task in a String format.
     * @return the end date of the Task.
     */
    public LocalDate getTaskDate() {
        return this.taskDate;
    }

    /**
     * Returns the end time of this Task in a String format.
     * @return the end time of the Task.
     */
    public LocalTime getTaskTime() {
        return this.taskTime;
    }


    /**
     * Sets the name of this Task to a new name.
     * @param taskName the name to change this Task to.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Sets the end date of this Task to a new Date.
     * @param taskDate the new Date.
     * @throws DateTimeParseException if the Date is in the wrong format.
     */
    public void setTaskDate(String taskDate) throws DateTimeParseException {
        this.taskDate = LocalDate.parse(taskDate, DATE_FORMATTER);
    }

    /**
     * Sets the end time of this Task to a new Time.
     * @param taskTime the new Time.
     * @throws DateTimeParseException if the Time is in the wrong format
     */
    public void setTaskTime(String taskTime) throws DateTimeParseException {
        this.taskTime = LocalTime.parse(taskTime, TIME_FORMATTER);
    }

    /**
     * Marks this Task as done, if it has not already been marked as done.
     * @return a String message indicating if the Task has been marked done.
     */
    public String markDone() {
        if (this.isDone) {
            return Ui.TASK_ALREADY_DONE;
        }
        this.isDone = true;
        return Ui.MARKED_AS_DONE + this;
    }
}
