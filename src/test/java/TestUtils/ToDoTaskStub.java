package testutils;

import model.ToDoTask;

public class ToDoTaskStub extends ToDoTask {
    public static final String DEFAULT_DESCRIPTION = "complete ip increments";

    private String description;
    private boolean isDone;

    public ToDoTaskStub() {
        description = DEFAULT_DESCRIPTION;
        isDone = false;
    }

    public ToDoTaskStub withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public void markAsDone() {
        this.isDone = true;
    }
}
