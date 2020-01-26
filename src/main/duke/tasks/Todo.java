package main.duke.tasks;

import main.duke.enums.TType;

import java.time.LocalDate;

public class Todo extends Task {

    public TType getType() {
        return TType.TODO;
    }

    public LocalDate getDate() {
        return null;
    }

    public Todo(int id, String task) {
        super(id, task);
    }

    public Todo(int id, boolean done, String task) {
        super(id, done, task);
    }

    @Override
    public String toString() {
        if (done) return  "[T][✓] " + task;
        else return "[T][✗] " + task;
    }
}
