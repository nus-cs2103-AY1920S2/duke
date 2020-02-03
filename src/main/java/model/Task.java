package main.java.model;

import main.java.exceptions.NoDescriptionException;

public class Task {
    protected String taskType;
    protected String description;
    protected boolean isDone;

    public Task(String taskType) {
        this.taskType = taskType;
    };

    public Task(String description, String taskType) throws NoDescriptionException {
        this.taskType = taskType;
        if ("".equals(description)) {
            throw new NoDescriptionException("OOPS!!! The description of a " +
                    this.taskType + " cannot be empty.\n");
        }
        this.description = description;
        this.isDone = false;
    }

    public void setDescription(String description) throws NoDescriptionException {
        if ("".equals(description)) {
            throw new NoDescriptionException("OOPS!!! The description of a " +
                    this.taskType + " cannot be empty.\n");
        }
        this.description = description;
    }

    public void setParams(String... params) throws NoDescriptionException {
        this.setDescription(params[0]);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.getDescription();
    }
}