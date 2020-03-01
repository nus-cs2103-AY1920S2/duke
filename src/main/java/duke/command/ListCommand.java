package duke.command;

import duke.core.Storage;
import duke.core.Ui;
import duke.core.Message;
import duke.task.TaskList;

import duke.exception.InvalidCommandException;

public class ListCommand extends Command {
    public ListCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        if (input.trim().compareTo("list") == 0) {
            return tasks.toString();
        } else {
            throw new InvalidCommandException(Message.LIST_ERROR);
        }
    }
}