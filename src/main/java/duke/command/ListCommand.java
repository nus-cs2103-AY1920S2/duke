package duke.command;

import duke.core.Storage;
import duke.core.Ui;
import duke.core.Message;
import duke.task.TaskList;

import duke.exception.InvalidCommandException;

/**
 * Represents a list tasks command.
 */
public class ListCommand extends Command {
    /**
     * Represents a list command.
     * @param input The user input.
     */
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Executes the List command for the program to show a list of tasks.
     * @param tasks Tasklist for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke
     * @return A String containing the response from the executed method.
     * @throws InvalidCommandException An exception telling ifthe command is invalid.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        if (input.trim().compareTo("list") == 0) {
            return tasks.toString();
        } else {
            throw new InvalidCommandException(Message.LIST_ERROR);
        }
    }
}