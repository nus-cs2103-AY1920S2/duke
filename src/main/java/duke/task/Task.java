package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;
    protected final static DateTimeFormatter IN_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected final static DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy KK:mm a");

    /**
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = TaskType.X; //not using type
    }

    /**
     * Get the icon represent task status.
     *
     * @return task status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[y]": "[n]");
    }

    /**
     * Get the status of the task.
     *
     * @return boolean represent status
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Get type of the task.
     *
     * @return type of the task
     */
    public TaskType getTaskType() {
        return this.taskType;
    }

    /**
     * Get description of the task.
     *
     * @return description string
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Change the task status to done.
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

    /**
     * Convert task to string to store in the data file.
     *
     * @return string represent the task
     */
    public String saveString() {
        return taskType + " | " + (isDone ? "1" : "0") + " | " + description + " | ";
    }
}
