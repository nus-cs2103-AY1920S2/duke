package main.java.model;

import main.java.model.Task;

import main.java.exceptions.NoDescriptionException;

public class DeadLineTask extends Task {
    protected String by;

    public DeadLineTask() {
        super("deadline task");
    }

    public DeadLineTask(String description, String by) throws NoDescriptionException {
        super(description, "deadline task");
        this.by = by;
    }

    public DeadLineTask(String... params) throws NoDescriptionException {
        super(params[0], "deadline task");
        this.by = params[1];
    }

    @Override
    public void setParams(String... params) throws NoDescriptionException {
        this.setDescription(params[0]);
        this.by = params[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
