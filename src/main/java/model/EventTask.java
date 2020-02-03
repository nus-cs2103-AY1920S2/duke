package main.java.model;

import main.java.model.Task;

import main.java.exceptions.NoDescriptionException;

public class EventTask extends Task{
    protected String at;

    public EventTask() {
        super("event task");
    }

    public EventTask(String description, String at) throws NoDescriptionException {
        super(description, "event task");
        this.at = at;
    }

    @Override
    public void setParams(String... params) throws NoDescriptionException {
        this.setDescription(params[0]);
        this.at = params[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
