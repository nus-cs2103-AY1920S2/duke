package duke.command;

import duke.core.Ui;
import duke.core.Message;
import duke.core.Storage;
import duke.task.TaskList;

import duke.exception.InvalidCommandException;

public class ByeCommand extends Command {
    public ByeCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        if (input.trim().compareTo("bye") == 0) {
            return ui.printGoodbye();
        } else {
            throw new InvalidCommandException(Message.COMMAND_ERROR);
        }
    }
}