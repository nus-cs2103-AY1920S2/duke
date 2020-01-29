package com.duke.task;

import java.time.LocalDate;

public class Task {
    protected String description;
    public boolean isDone;

    public Task(String d) {
        this.description = d;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String generateWriteFormat() {
        return null;
    }

    public static LocalDate generateTime(String input) {
        return LocalDate.parse(input);

    }

}
