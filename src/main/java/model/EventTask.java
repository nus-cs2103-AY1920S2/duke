package main.java.model;

import main.java.model.Task;

import main.java.exceptions.NoDescriptionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task{
    protected LocalDateTime at;

    public EventTask() {
        super("event task");
    }

    public EventTask(String description, LocalDateTime at) throws NoDescriptionException {
        super(description, "event task");
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DATE_TIME_FORMAT) + ")";
    }
}
