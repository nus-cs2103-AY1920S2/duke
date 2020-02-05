package main.java.model;

import main.java.model.Task;

import main.java.exceptions.NoDescriptionException;

public class EventTask extends Task{
    static final String TASK_TYPE_STRING = "event task";
    static final String TASK_TYPE_CHA = "E";

    protected String at;

    public EventTask() {}

    public EventTask(String description, String at) throws NoDescriptionException {
        super(description);
        this.at = at;
    }

    public EventTask(String... params) throws NoDescriptionException {
        super(params[0]);
        this.at = params[1];
    }

    @Override
    public void setParams(String... params) throws NoDescriptionException {
        this.setDescription(params[0]);
        this.at = params[1];
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE_CHA + "]"  + super.toString() + " (at: " + at + ")";
    }
}
