package main.java.model;

import main.java.model.Task;

import main.java.exceptions.NoDescriptionException;

import java.util.ArrayList;
import java.util.Arrays;

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
    public ArrayList<String> getDetails() {
        return new ArrayList<String>(Arrays.asList(
                this.description,
                this.at));
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE_CHA;
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
