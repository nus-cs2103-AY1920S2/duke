package duke.command;

import duke.core.Message;
import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;
import duke.exception.InvalidCommandException;
import duke.exception.KeywordNotFoundException;

public class FindCommand extends Command {
    public FindCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException, KeywordNotFoundException {
        String[] split = input.split(" ");
        if (split.length != 2) {
            throw new InvalidCommandException(Message.FIND_ERROR);
        }
        return tasks.findKeyword(input);
    }
}