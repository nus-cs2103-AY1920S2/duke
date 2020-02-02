package com.duke.bot;

public class Task {
    protected String taskName;
    protected boolean isDone;

    protected Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public static Task createTask(String taskName) {
        return new Task(taskName, false);
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718");
        return (isDone ? "Done" : "Not Done");
    }

    public void markDone() {
        isDone = true;
    }

    public String getTaskIcon() {
        return "O";
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s]%s", getTaskIcon(), getStatusIcon(), taskName);
    }
}
