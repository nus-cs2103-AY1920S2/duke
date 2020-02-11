package duke.command;

import duke.exception.DukeException;
import duke.exception.InsufficientArgumentsException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String[] cmdArgs) throws DukeException {
        if (!hasValidNumOfArgs(cmdArgs.length)) {
            throw new InsufficientArgumentsException("☹ OOPS!!! FIND command expects a search term!");
        } else {
            this.searchTerm = cmdArgs[1];
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Ui.printMessage(tasks.findTask(searchTerm));
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
