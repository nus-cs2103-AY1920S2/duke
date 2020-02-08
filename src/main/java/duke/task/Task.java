package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** An abstract task class to be extended for specific tasks. */
public abstract class Task {

    /** Task name that describes the nature of the task */
    protected String taskName = "";

    /** Whether the task is completed. */
    protected boolean isDone = false;

    /** Date and time of the task (if any). */
    protected LocalDate dateTime = LocalDate.MAX;

    /** Type of task. */
    protected String taskType = "";

    /** Standard input date formatter for Duke program */
    protected final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Standard output date formatter for Duke program */
    protected final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Constructs a task (for ToDo tasks).
     *
     * @param taskName Describes the nature of the task.
     */
    public Task(String taskName, String taskType) {
        this.taskName = taskName.trim();
        this.taskType = taskType;
    }

    /**
     * Constructs a task with additional details (for Event or Deadline tasks).
     *
     * @param taskName Describes the nature of the task.
     * @param dateTime The date and time of the task (if any).
     * @throws DukeException If date format is incorrect.
     */
    public Task(String taskName, String taskType, String dateTime) throws DukeException {
        this.taskName = taskName.trim();
        this.taskType = taskType;
        try {
            this.dateTime = LocalDate.parse(dateTime.trim(), inputFormatter);
        } catch (Exception e) {
            throw new DukeException("Please give the appropriate date format in yyyy-MM-dd!");
        }
    }

    /**
     * Constructs a task with additional details (for ToDo tasks).
     *
     * @param taskName Describes the nature of the task.
     * @param isDone Indicates if the task is completed already.
     */
    public Task(String taskName, String taskType, boolean isDone) {
        this.taskName = taskName.trim();
        this.taskType = taskType;
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
    public Task(String taskName, String taskType, boolean isDone, String dateTime) throws DukeException {
        this.taskName = taskName.trim();
        this.taskType = taskType;
        this.isDone = isDone;
        try {
            this.dateTime = LocalDate.parse(dateTime.trim(), inputFormatter);
        } catch (Exception e) {
            throw new DukeException("Please give the appropriate date format in yyyy-MM-dd!");
        }
    }

    public void completeTask() {
        this.isDone = true;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public LocalDate getTaskDate() {
        return this.dateTime;
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
        return String.format("%s_%s_%s_%s", taskType, isDone, taskName, dateTime);
    }

    /**
     * Outputs date along with task if it is an event or deadline. If it is a to-do task, then just output task.
     * Max value of local date is assigned for to-do task and used to differentiate between event and deadline from
     * to-do task.
     *
     * @return Tasks in appropriate string output format.
     */
    public String toString() {
        String output = String.format("[%s][%s] %s", taskType, getStatusIcon(), taskName);
        if (this.dateTime.equals(LocalDate.MAX)) {
            return output;
        } else {
            return String.format("%s (at: %s)", output, this.dateTime.format(outputFormatter));
        }
    }

}
