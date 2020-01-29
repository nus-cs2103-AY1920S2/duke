package com.duke.task;

import java.time.LocalDate;

/**
 * Represents a task specified by the user.
 */
public class Task {
    protected String description;
    public boolean isDone;

    public Task(String d) {
        this.description = d;
        this.isDone = false;
    }

    /**
     * Generates the icon representation of the status of the task.
     * @return the icon representation of the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
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
