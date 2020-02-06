package main.java.model;

import main.java.model.Task;

import main.java.exceptions.NoDescriptionException;

import java.time.LocalDateTime;

public class DeadLineTask extends Task {
    protected LocalDateTime by;

    public DeadLineTask() {
        super("deadline task");
    }

    public DeadLineTask(String description, LocalDateTime by) throws NoDescriptionException {
        super(description, "deadline task");
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DATE_TIME_FORMAT) + ")";
    }
}
