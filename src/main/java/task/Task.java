package task;

import java.time.LocalDateTime;

public class Task {

    private String taskAction;
    private boolean isDone;
    protected int taskDuplicatesNumber;

    public Task(String taskAction) {
        this.taskAction = taskAction;
        this.isDone = false;
        this.taskDuplicatesNumber = 1;
    }

    /**
     * Marks this task as done.
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the current status of the task.
     *
     * @return boolean.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Gets the taskAction of the task.
     *
     * @return String.
     */
    public String getTaskAction() {
        return this.taskAction;
    }

    /**
     * Represents task as a string.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return String.format("[^] %s", this.taskAction);
        } else {
            return String.format("[x] %s", this.taskAction);
        }
    }

    public String toStringForFileStorage() {
        return "";
    }
}
