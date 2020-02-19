package parser;

import exceptions.NoDescriptionException;
import model.Task;
import model.ToDoTask;

/**
 * Presents a command to add todotask into inner-task list.
 */
public class AddToDoCommand extends Command {
    private String description;

    /**
     * Constructs an {@code AddToDoCommand}.
     * @param description A valid description for a todotask.
     */
    AddToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Create a todotask with the inputted description and add it to inner-tasklist.
     * @return response from the TaskList class as a string.
     * @throws NoDescriptionException If the description is empty.
     */
    @Override
    public String execute() throws NoDescriptionException {
        Task taskToAdd = new ToDoTask(description);
        return this.taskList.add(taskToAdd);
    }
}
