package model;

import exceptions.NoDescriptionException;

public class ToDoTask extends Task {
    static final String TASK_TYPE_STRING = "todo task";
    static final String TASK_TYPE_CHA = "T";

    public ToDoTask(String description) throws NoDescriptionException {
        super(description);
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