package duke.command;

import duke.task.TaskList;
import duke.core.Ui;
import duke.core.Storage;

import duke.exception.EmptyDescriptionException;

/**
 * Represents a todo task command.
 */
public class TodoCommand extends Command {
    /**
     * Constructs a fresh instance of a todo command.
     * @param input The user input.
     */
    public TodoCommand(String input) {
        super(input);
    }

    /**
     * Executes the Todo command for the program to create a todo.
     * @param tasks Tasklist for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke
     * @return A String containing the response from the executed method.
     * @throws EmptyDescriptionException The exception when an empty description is provided.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        return tasks.manageTodo(storage, input);
    }
}