package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** An abstract task class to be extended for specific tasks. */
public abstract class Task {

    /** Task name that describes the nature of the task */
    protected String taskName = "";

    /** Whether the task is completed. */
    protected boolean isDone;

    /** Date and time of the task (if any). */
    protected LocalDate dateTime;

    /** Type of task. */
    protected String taskType = "";

    /**
     * Constructs a task.
     *
     * @param taskName Describes the nature of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Constructs a task with additional details.
     *
     * @param taskName Describes the nature of the task.
     * @param dateTime The date and time of the task (if any).
     * @throws DukeException If date format is incorrect.
     */
    public Task(String taskName, String dateTime) throws DukeException {
        this.taskName = taskName;
        this.isDone = false;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.dateTime = LocalDate.parse(dateTime, formatter);
        } catch (Exception e) {
            throw new DukeException("Please give the appropriate date format in yyyy-MM-dd " +
                    "(if date is not applicable, please enter 2100-01-01)");
        }
    }

    /**
     * Constructs a task with additional details (for ToDo tasks).
     *
     * @param taskName Describes the nature of the task.
     * @param isDone Indicates if the task is completed already.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Constructs a task with additional details (for Event or Deadline tasks).
     *
     * @param taskName Describes the nature of the task.
     * @param isDone Indicates if the task is completed already.
     * @param dateTime The date and time of the task (if any).
     * @throws DukeException If date format is incorrect.
     */
    public Task(String taskName, boolean isDone, String dateTime) throws DukeException {
        this.taskName = taskName;
        this.isDone = isDone;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.dateTime = LocalDate.parse(dateTime, formatter);
        } catch (Exception e) {
            throw new DukeException("Please give the appropriate date format in yyyy-MM-dd " +
                    "(if date is not applicable, please enter 2100-01-01)");
        }
    }

    public void completeTask() {
        this.isDone = true;
    }


    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns icon to display completion status of the task.
     *
     * @return Icon status.
     */
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    /**
     * Converts the task into a format for saving to text file.
     *
     * @return Task in the appropriate string format for saving.
     */
    public String getSaveFormat() {
        return this.taskType + "_" + this.isDone + "_" + this.taskName + "_" + this.dateTime;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }

}
