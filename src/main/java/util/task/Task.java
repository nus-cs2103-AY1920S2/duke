package util.task;

import util.DukeConstant;

/**
 * Represents an abstract class, util.task.Task.
 * A util.task.Task has a description and a complete status.
 */
public abstract class Task {
    String description;
    private boolean isDone;

    /**
     * Creates a util.task.Task.
     *
     * @param description A String description of the util.task.Task.
     * @param isDone      A boolean on whether the util.task.Task is complete.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * An abstract task to complete the util.task.Task.
     *
     * @return A completed util.task.Task with the same description.
     */
    public abstract Task completeTask();

    /**
     * Obtain a status icon, depending on the complete status.
     *
     * @return A String status icon.
     */
    public String getStatusIcon() {
        return (isDone ? DukeConstant.TICK_ICON : DukeConstant.CROSS_ICON); //return tick or X symbols
    }

    public boolean getCompletionStatus() {
        return this.isDone;
    }

    public boolean match(String keywords) {
        assert keywords != null;
        return this.description.toLowerCase().contains(keywords);
    }
}
