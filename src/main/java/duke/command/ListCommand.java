package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showTaskList(tasks);
    }
}
