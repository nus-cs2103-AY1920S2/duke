package com.duke.bot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks that occur at a specific date.
 */
public class EventTask extends Task {
    private LocalDate due;

    private EventTask(String taskName, LocalDate due) {
        super(taskName, false);
        this.due = due;
    }

    /**
     * Creates an Event Task.
     *
     * @param taskName The name of the Event Task.
     * @param due The date of happening of the Event Task.
     * @return an Event Task object.
     */
    public static EventTask createEventTask(String taskName, LocalDate due) {
        return new EventTask(taskName, due);
    }

    /**
     * Creates a message containing the name and due date of the task.
     *
     * @return A string that contains the name and due date of the task.
     */
    @Override
    public String toString() {
        return super.toString().concat(String.format(" | %s", due.format(
                DateTimeFormatter.ofPattern("dd-MMM-yyyy"))));
    }

    /**
     * Returns the task icon of the Event Task.
     *
     * @return A string 'E'.
     */
    @Override
    public String getTaskIcon() {
        return "E";
    }
}
