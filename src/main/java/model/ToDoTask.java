package main.java.model;

import main.java.model.Task;

import main.java.exceptions.NoDescriptionException;

public class ToDoTask extends Task {
    static final String TASK_TYPE_STRING = "todo task";
    static final String TASK_TYPE_CHA = "T";

    public ToDoTask() {}

    public ToDoTask(String description) throws NoDescriptionException {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE_CHA + "]"  + super.toString();
    }
}