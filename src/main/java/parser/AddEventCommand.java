package parser;

import exceptions.NoDescriptionException;
import model.EventTask;
import model.Task;

import java.time.LocalDateTime;

/**
 * Presents a command to add event task into inner-task list.
 */
public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime at;

    /**
     * Constructs an {@code AddEventCommand}.
     * @param description A valid description for a event task.
     */
    AddEventCommand(String description, LocalDateTime at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Create a event task with the inputted description and add it to inner-tasklist.
     * @return response from the TaskList class as a string.
     * @throws NoDescriptionException If the description is empty.
     */
    @Override
    public String execute() throws NoDescriptionException {
        Task taskToAdd = new EventTask(description, at);
        return this.taskList.add(taskToAdd);
    }
}
