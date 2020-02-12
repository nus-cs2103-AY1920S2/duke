package model;

import exceptions.NoDescriptionException;

public class ToDoTask extends Task {
    private static final String TASK_TYPE_STRING = "todo task";
    private static final String TASK_TYPE_CHA = "T";

    public ToDoTask() {}

    public ToDoTask(String description) throws NoDescriptionException {
        super(description, TASK_TYPE_STRING);
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE_CHA;
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE_CHA + "]"  + super.toString();
    }
}