package command;

import task.Event;
import task.RecurringTask;

import java.time.LocalDate;

/**
 * Specific type of command that add recurring task to the storage.
 */
public class AddRecurringCommand extends AddTaskCommand {
    /**
     * Constructor for command that add a event task.
     *
     * @param description contains the description of the event task.
     * @param time        contains the event time of the task.
     */
    public AddRecurringCommand(String description, LocalDate time) {
        super(new RecurringTask(description, time));
    }
}
