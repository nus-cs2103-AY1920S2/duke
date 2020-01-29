package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showAllTask(tasks);
    }
}
