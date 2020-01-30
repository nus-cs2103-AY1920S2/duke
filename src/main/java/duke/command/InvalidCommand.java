package duke.command;

import duke.exception.UnknownCommandException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UnknownCommandException {
        throw new UnknownCommandException();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}