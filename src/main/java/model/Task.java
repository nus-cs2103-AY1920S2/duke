package main.java.model;

import main.java.exceptions.NoDescriptionException;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    static String TASK_TYPE_STRING;
    static String TASK_TYPE_CHA;

    public Task() {}

    public Task(String description) throws NoDescriptionException {
        if ("".equals(description)) {
            throw new NoDescriptionException("OOPS!!! The description of a " +
                    TASK_TYPE_STRING + " cannot be empty.\n");
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