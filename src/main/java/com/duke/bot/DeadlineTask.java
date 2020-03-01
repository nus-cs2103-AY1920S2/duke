package com.duke.bot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks that has a deadline.
 */
public class DeadlineTask extends Task {
    private LocalDate due;

    private DeadlineTask(String taskName, LocalDate due) {
        super(taskName, false);
        this.due = due;
    }

    /**
     * Creates a DeadlineTask.
     *
     * @param taskName The name of the deadline task.
     * @param due the due date of the deadline task.
     * @return A deadlineTask.
     */
    public static DeadlineTask createDeadlineTask(String taskName, LocalDate due) {
        return new DeadlineTask(taskName, due);
    }

    @Override
    public String toString() {
        assert getTaskIcon().equals("D") : "Deadline task icon should be D";
        return super.toString().concat(String.format(
                " | %s", due.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))));
    }

    /**
     * Returns the status icon of a deadline task, which is 'D'.
     */
    @Override
    public String getTaskIcon() {
        return "D";
    }
}
