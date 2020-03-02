package duke.command;

import duke.core.Ui;
import duke.core.Message;
import duke.core.Storage;
import duke.task.TaskList;

import duke.exception.InvalidCommandException;

/**
 * Represents a ByeCommand when the user command is Bye.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a fresh instance of ByeCommand.
     * @param input User input for the command.
     */
    public ByeCommand(String input) {
        super(input);
    }

    /**
     * Executes the Bye command for the program to exit.
     * @param tasks Tasklist for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke
     * @return A String containing the response from the executed method.
     * @throws InvalidCommandException An exception telling the user that the command is invalid.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        if (input.trim().compareTo("bye") == 0) {
            return ui.goodbyeMessage();
        } else {
            throw new InvalidCommandException(Message.COMMAND_ERROR);
        }
    }
}