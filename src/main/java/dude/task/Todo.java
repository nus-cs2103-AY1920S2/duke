package dude.task;

import java.time.LocalDate;

public class Todo extends Task {
    public Todo(String details, boolean isDone) {
        super(details, isDone);
    }

    @Override
    public boolean occursOn(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String storeFormat() {
        return String.format("T|%s|%s", getStatusIcon(), getDetails());
    }
}