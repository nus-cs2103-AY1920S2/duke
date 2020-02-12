package model;

import exceptions.NoDescriptionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    private static final String TASK_TYPE_STRING = "task";
    static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Task() {}
    
    public Task(String description) throws NoDescriptionException {
        if ("".equals(description)) {
            throw new NoDescriptionException("OOPS!!! The description of a " +
                    TASK_TYPE_STRING + " cannot be empty.\n");
        }
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String taskTypeString) throws NoDescriptionException {
        if ("".equals(description)) {
            throw new NoDescriptionException("OOPS!!! The description of a " +
                    taskTypeString + " cannot be empty.\n");
        }
        this.description = description;
        this.isDone = false;
    }

    public void setDescription(String description) throws NoDescriptionException {
        if ("".equals(description)) {
            throw new NoDescriptionException("OOPS!!! The description of a " +
                    TASK_TYPE_STRING + " cannot be empty.\n");
        }
        this.description = description;
    }

    public void setParams(String... params) throws NoDescriptionException {
        this.setDescription(params[0]);
    }

    public abstract String getTaskType();

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<String> getDetails() {
        return new ArrayList<String>(Arrays.asList(this.description));
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.getDescription();
    }
}