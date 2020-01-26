package main.java.duke.tasks;

import main.java.duke.enums.TType;

import java.time.LocalDate;

public class Todo extends Task {

    public TType getType() {
        return TType.TODO;
    }

    public LocalDate getDate() {
        return null;
    }

    public Todo(String task) {
        super(task);
    }

    public Todo(boolean done, String task) {
        super(done, task);
    }

    @Override
    public String toString() {
        if (done) return  "[T][✓] " + task;
        else return "[T][✗] " + task;
    }
}
