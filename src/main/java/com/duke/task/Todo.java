package com.duke.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    /**
     * Creates a Todo task with the description given.
     * @param d description of the Todo task.
     */
    public Todo(String d){
        super(d);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String generateWriteFormat() {
        int k = this.isDone ? 1 : 0;
        return "T|"+ k + "|" + description;
    }
}
