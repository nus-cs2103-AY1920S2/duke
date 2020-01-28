package duke.Task;

import java.time.LocalDateTime;

/**
 * @ version 1.0
 * @ since 2020-01-28
 */
public class Task {

    protected String taskDescription;
    protected LocalDateTime dateTime;
    protected status isDone;

    public enum Types {
        ToDo, Deadline, Event;
    }

    public enum status {
        Y, N;
    }

    public Task(LocalDateTime dateTime, String taskDescription) {

        this.taskDescription = taskDescription;
        this.dateTime = dateTime;
        this.isDone = status.N;

    }

    /**
     * Updates the status of the task
     * @param newStatus
     */
    public void changeStatus(status newStatus) {

        isDone = newStatus;

    }

    /**
     * Gets date and time related to the task
     * @return date and time of task
     */
    public LocalDateTime getDateTime() {

        return dateTime;

    }

    /**
     * Gets status of the task
     * @return status of task
     */
    public status getStatus() {

        return isDone;

    }

    /**
     * Gets task description of the task
     * @return task description of task
     */
    public String getTaskDescription() {

        return taskDescription;

    }

    /**
     * Gets the type of the task
     * @return type of task
     */
    public Task.Types getType() {
        return this.getType();
    }

    /**
     * Constructs a string representation of task
     * @return string representation of task
     */
    @Override
    public String toString() {

        return this.toString();

    }
}
