package main.java.model;

import main.java.model.Task;

import main.java.exceptions.NoDescriptionException;

public class DeadLineTask extends Task {
    static final String TASK_TYPE_STRING = "deadline task";
    static final String TASK_TYPE_CHA = "D";

    protected String by;

    public DeadLineTask() {}

    public DeadLineTask(String description) throws NoDescriptionException {
        super(description);
        this.by = by;
    }

    public DeadLineTask(String... params) throws NoDescriptionException {
        super(params[0]);
        this.by = params[1];
    }

    @Override
    public void setParams(String... params) throws NoDescriptionException {
        this.setDescription(params[0]);
        this.by = params[1];
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE_CHA + "]" + super.toString() + " (by: " + by + ")";
    }
}
