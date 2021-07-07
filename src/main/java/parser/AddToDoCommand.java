package parser;

import static parser.Parser.TODO_PATTERN;

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
     * @param userInput input from user.
     */
    AddToDoCommand(String userInput) {
        description = findDescription(TODO_PATTERN, userInput);
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
