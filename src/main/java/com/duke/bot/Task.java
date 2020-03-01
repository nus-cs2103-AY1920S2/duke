package com.duke.bot;

/**
 * Represents the tasks that can be managed by Duke Bot.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;
    //protected TaskType taskType = TaskType.OTHER;

    protected Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Factory method for creating a Task object.
     *
     * @param taskName The name of the task.
     * @return a task object.
     */
    public static Task createTask(String taskName) {
        return new Task(taskName, false);
    }

    /**
     * Returns the name of the task.
     *
     * @return The task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns the icon indicating if the task is a completed.
     *
     * @return a symbol.
     */
    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718");
        return (isDone ? "Done" : "Not Done");
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Returns the icon that represents the type of task.
     *
     * @return A character 'O'.
     */
    public String getTaskIcon() {
        return "O";
    }

    /**
     * Returns the completeness of the task.
     *
     * @return The boolean value that represents if the task is completed.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Prints the completeness, task type and the name of the task.
     *
     * @return the String containing the the information of the task.
     */
    @Override
    public String toString() {
        return String.format("%s | %s | %s", getTaskIcon(), getStatusIcon(), taskName);
    }
}
