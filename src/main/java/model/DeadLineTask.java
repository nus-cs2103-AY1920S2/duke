package main.java.model;

import main.java.model.Task;

import main.java.exceptions.NoDescriptionException;

public class DeadLineTask extends Task {
    protected String by;

    public DeadLineTask(String description, String by) throws NoDescriptionException {
        super(description, "deadline task");
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
