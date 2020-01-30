package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.dukePrompt("Good bye, boss! Call me if you need me. I'll be waiting!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}