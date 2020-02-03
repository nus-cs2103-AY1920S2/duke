package main.java.model;

import main.java.model.Task;

import main.java.exceptions.NoDescriptionException;

public class ToDoTask extends Task {

    public ToDoTask(String description) throws NoDescriptionException {
        super(description, "todo task");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}