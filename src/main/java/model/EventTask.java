package main.java.model;

import main.java.model.Task;

import main.java.exceptions.NoDescriptionException;

public class EventTask extends Task{
    protected String at;

    public EventTask(String description, String at) throws NoDescriptionException {
        super(description, "event task");
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
